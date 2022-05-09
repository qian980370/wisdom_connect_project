<template>
  <div style="padding: 10px; width: 100%">
    <div style="margin: 10px 0">
      <span>current user: {{user.username}}</span>
    </div>
<!--    add file-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="addFile">Upload File</el-button>
    </div>
<!--    search file by file name-->
    <div style="margin: 10px 0">
      <el-input v-model="query" placeholder="please input information of file name to search" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 7px" @click="load">Search</el-button>
    </div>
<!--table of data-->
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID">
      </el-table-column>

      <el-table-column prop="name" label="File Name">
      </el-table-column>

      <el-table-column label="img">
        <template #default="imgScope">
          <el-image style="width: 100px; height: 100px" :src="imgScope.row.url" />
        </template>
      </el-table-column>

      <el-table-column prop="type" label="File Type">
      </el-table-column>

      <el-table-column prop="size" label="Size(kb)">
      </el-table-column>

      <el-table-column prop="md5" label="MD5">
      </el-table-column>

      <el-table-column prop="url" label="URL">
      </el-table-column>

      <el-table-column prop="isDelete" label="is_delete">
      </el-table-column>

      <el-table-column label="Enable">
        <template #default="enableScope">
          <el-switch :disabled=this.checkSpecial(enableScope.row) v-model="enableScope.row.enable" active-value="1" inactive-value="0" active-color="#13ce66" inactive-color="#ccc" @change="changeEnable(enableScope.row.cid, enableScope.row)"></el-switch>
        </template>
      </el-table-column>



      <el-table-column fixed="right" label="Operations" width="200px">
        <template #default="scope">
          <el-button type="text" size="small" @click="handleDownload(scope.row)">Download</el-button>
          <el-popconfirm title="Are you sure to delete this?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="text" size="small" >Delete</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>

    </el-table>
<!--page control-->
    <div style="margin-top: 15px">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
      />



    </div>
<!--    upload file form-->
    <el-dialog v-model="dialogFileVisible" :title=dialogFileTitle width="30%">
      <div style="width: 85%">
        <el-upload
            class="avatar-uploader"
            action="http://localhost:9090/file/upload"
            :show-file-list="false"
            :on-change="handleLicensePreview"
            :before-upload="beforeLicenseUpload"
            :on-success="handleAvatarSuccess"
            :headers="uploadHeaders"
        >
          <img v-if="this.img != null" :src="this.img" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>

        <div style="margin-top: 10px">
          <el-button @click="dialogFileVisible = false">Cancel</el-button>
        </div>


      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
import router from "@/router";
import { Plus } from '@element-plus/icons-vue';

export default {
  name: 'FileControllerView',
  components: {
    Plus
  },
  data(){
    return{
      dialogFileTitle:"Create new profile",
      dialogFileVisible: false,
      version: Math.random(),
      query: '',
      total: 0,
      currentPage: 1,
      pageSize: 10,
      pageNum: 10,
      search: '',
      tableData:[

      ],
      img: null,
      user : localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null,
      uploadHeaders:{},
    }

  },
  created() {
    this.load();
    // console.log(this.user);
    this.uploadHeaders.token = this.user.token;
    // console.log(this.uploadHeaders);
  },
  methods:{
    checkSpecial(row){
      if(row.md5 === "special"){
        return true;
      }
      return false;
    },
    handleLicensePreview(res){
      const isLt2M = res.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$message.error('Avatar picture size can not exceed 2MB!');
      } else {
        //store file
        // this.img = URL.createObjectURL(res.raw);
      }
    },
    handleAvatarSuccess(res){
      // console.log(res);
      this.dialogFileVisible = false;
      this.img = null;
      // console.log(this.img)
      this.load();
      this.$message({
        type: "success",
        message: "Successfully upload file"
      })
    },
    beforeLicenseUpload(res){
      //console.log(res);
      if (res.type !== 'image/jpeg' && res.type !== 'image/png' && res.type !== 'image/jpg') {
        this.$message({
          type: "error",
          message: 'Avatar picture must be JPG format!'
        })
        return false
      } else if (res.size / 1024 / 1024 > 2) {
        this.$message({
          type: "error",
          message: 'Avatar picture size can not exceed 2MB!'
        })
        return false
      }
      return true

    },
    changeEnable(id, row){
      request.post("/file/update", row).then(res =>{
        //console.log(res);
        if (res.code === "200"){
          this.$message({
            type: "success",
            message: "Successfully change enable property of file. Please Refresh Page"
          })
        }else{
          this.$message({
            type: "error",
            message: res.msg
          })
          // if (this.tableData[id].enable === "1"){
          //   this.tableData[id].enable = "0";
          // }else{
          //   this.tableData[id].enable = "1";
          // }

        }
        //location.reload();
      });
    },
    refreshUser(){
      this.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    },
    // handleClose(){
    //   this.dialogVisible = false;
    // },
    load(){
      this.refreshUser();
      request.get("/file/page", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          query: this.query
        }
      }).then(res =>{
        //console.log(res);
        this.tableData = res.data.records;
        this.total = res.data.total;
      })
    },
    addFile(){
      this.img = null;
      this.dialogFileVisible = true;
      this.fileForm = {};
      this.dialogFileTitle = "Upload new file";
    },
    handleDownload(row){
      let url;
      url = JSON.parse(JSON.stringify(row.url));
      window.open(url);
      //(url);
    },
    handleDelete(id){
      //console.log(id)
      request.delete("/file/" + id).then(res => {
        if (res.code === '200') {
          this.$message({
            type: "success",
            message: "successfully delete"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()
      })
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum){
      this.currentPage = pageNum;
      this.load();
    },
  },

}



</script>
<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>