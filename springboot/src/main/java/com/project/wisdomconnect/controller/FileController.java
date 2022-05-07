package com.project.wisdomconnect.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.wisdomconnect.common.Constants;
import com.project.wisdomconnect.common.Result;
import com.project.wisdomconnect.entity.Files;
import com.project.wisdomconnect.entity.User;
import com.project.wisdomconnect.mapper.FileMapper;
import com.project.wisdomconnect.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Objects;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Value("${files.download.path}")
    private String fileDownloadPath;

    private String disableImg = "unavailable.jpg";
    @Resource
    private FileMapper fileMapper;

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String query){
        LambdaQueryWrapper<Files> wrapper = Wrappers.<Files>lambdaQuery().orderByAsc(Files::getId);
        if (StrUtil.isNotBlank(query)) {
            wrapper.like(Files::getName, query);
        }
        //User checkUser = TokenUtils.getUser();
        //System.out.println("current user is: " + checkUser.getUsername());

        Page<Files> filesPage = fileMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(filesPage);

    }
    /**
     *
     * @param file upload file from front end
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        // Get file name from upload file
        String originalFilename = file.getOriginalFilename();
        // Get type of upload file
        String type = FileUtil.extName(originalFilename);
        // Get size of upload file (with kb, default is b)
        long size = file.getSize() / 1024;


        // Store file into disk
        // ParentFile is used to check store directory
        File uploadParentFolder = new File(fileUploadPath);
        // Check whether the file directory exists. If not, create a new directory
        if(!uploadParentFolder.exists()){
            // If not, create a new directory
            uploadParentFolder.mkdirs();
        }


        // set unique uid for file
        String uuid = IdUtil.fastSimpleUUID();
        // combine uuid with file type for easier searching in future
        String fileUuid = uuid + StrUtil.DOT + type;
        String fileUrl;
        // build upload file
        File uploadFile = new File(fileUploadPath + fileUuid);


        // build md5 code for upload file
        String md5 = SecureUtil.md5(file.getInputStream());
        // check is there any replicated file in database
        String searchFileUrl = getUrlByMd5(md5);
        if (searchFileUrl != null){ // if file have existed in database
            fileUrl = searchFileUrl; //directly get url
            uploadFile.delete();
        }else {
            // build url for file
            fileUrl = fileDownloadPath + fileUuid;
            // Store upload file into disk
            file.transferTo(uploadFile);
            // Store upload file into database
            Files databaseFile = new Files();
            databaseFile.setName(originalFilename);
            databaseFile.setType(type);
            databaseFile.setSize(size);
            databaseFile.setUrl(fileUrl);
            databaseFile.setMd5(md5);
            fileMapper.insert(databaseFile);
        }

        return fileUrl;
    }

    // down load url: http://localhost:9090/file/download/{fileUuid};
    @GetMapping("/download/{fileUuid}")
    public void download(@PathVariable String fileUuid, HttpServletResponse response) throws IOException {
        // use uuid to get file
        LambdaQueryWrapper<Files> wrapper = Wrappers.<Files>lambdaQuery().orderByAsc(Files::getId);
        String query = fileDownloadPath + fileUuid;
        wrapper.like(Files::getUrl, query);

        Files file = fileMapper.selectOne(wrapper);
        File uploadFile = null;
        if(Objects.equals(file.getEnable(), "0")){
            uploadFile = new File(fileUploadPath + disableImg);
        }else {
            uploadFile = new File(fileUploadPath + fileUuid);
        }


        ServletOutputStream os = response.getOutputStream();

        // basic response format setting
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUuid, "UTF-8"));
        response.setContentType("application/octet-stream");

        // read upload byte stream
        byte[] bytes = FileUtil.readBytes(uploadFile);
        os.write(bytes);
        os.flush();
        os.close();
    }

    /**
     * check is there any replicated file in database by md5
     * @param md5 input md5 code
     * @return file url if existed.
     */
    private String getUrlByMd5(String md5){
        String fileUrl = null;
        // check is there any replicated file in database
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        Files searchFile = fileMapper.selectOne(queryWrapper);
        if (searchFile != null){ // if file have existed in database
            fileUrl = searchFile.getUrl(); //directly get url
        }
        return fileUrl;
    }

    /**
     * delete file
     * @param id file's id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        if (id == 26){
            return Result.error(Constants.CODE_406, Constants.CODE_406_MESSAGE);
        }else {
            fileMapper.deleteById(id);
            return Result.success();
        }

    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody Files file) {
        if (file.getMd5().equals("special")){
            return Result.error(Constants.CODE_406, Constants.CODE_406_MESSAGE);
        }

        return Result.success(fileMapper.updateById(file));
    }
}
