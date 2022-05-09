package com.project.wisdomconnect.utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailUtils {

    private static String USER_NAME = "wisdomConnectOfficial";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "tupjflxtdniwhoci"; // GMail password
    private static String RECIPIENT = "wisdomConnectOfficial@gmail.com";


    public static void sendFromGMail(String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", USER_NAME);
        props.put("mail.smtp.password", PASSWORD);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(USER_NAME));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, USER_NAME, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import javax.mail.MessagingException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.io.File;
//
///**
// * 发送邮件工具类 MailUtil
// *
// * @author heshi
// * @date 2021/3/22 16:52
// */
//
//@Service
//@EnableAutoConfiguration
//public class MailUtils implements EmailService
//{
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    //Spring Boot 提供了一个发送邮件的简单抽象，使用的是下面这个接口，这里直接注入即可使用
//    @Autowired
//    private JavaMailSender mailSender;
//
//    // 配置文件中我的qq邮箱
//    @Value("${spring.mail.from}")
//    private String from;
//
//    /**
//     * 简单文本邮件
//     * @param to 收件人
//     * @param subject 主题
//     * @param content 内容
//     */
//    @Override
//    public void sendSimpleMail(String to, String subject, String content) {
//        //创建SimpleMailMessage对象
//        SimpleMailMessage message = new SimpleMailMessage();
//        //邮件发送人
//        message.setFrom(from);
//        //邮件接收人
//        message.setTo(to);
//        //邮件主题
//        message.setSubject(subject);
//        //邮件内容
//        message.setText(content);
//        //发送邮件
//        mailSender.send(message);
//    }
//
//    /**
//     * html邮件
//     * @param to 收件人,多个时参数形式 ："xxx@xxx.com,xxx@xxx.com,xxx@xxx.com"
//     * @param subject 主题
//     * @param content 内容
//     */
//    @Override
//    public void sendHtmlMail(String to, String subject, String content) {
//        //获取MimeMessage对象
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper messageHelper;
//        try {
//            messageHelper = new MimeMessageHelper(message, true);
//            //邮件发送人
//            messageHelper.setFrom(from);
//            //邮件接收人,设置多个收件人地址
//            InternetAddress[] internetAddressTo = InternetAddress.parse(to);
//            messageHelper.setTo(internetAddressTo);
//            //messageHelper.setTo(to);
//            //邮件主题
//            message.setSubject(subject);
//            //邮件内容，html格式
//            messageHelper.setText(content, true);
//            //发送
//            mailSender.send(message);
//            //日志信息
//            logger.info("Mail send successfully");
//        } catch (Exception e) {
//            logger.error("Mail send fail!", e);
//        }
//    }
//
//    /**
//     * 带附件的邮件
//     * @param to 收件人
//     * @param subject 主题
//     * @param content 内容
//     * @param filePath 附件
//     */
//    @Override
//    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
//        MimeMessage message = mailSender.createMimeMessage();
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom(from);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(content, true);
//
//            FileSystemResource file = new FileSystemResource(new File(filePath));
//            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
//            helper.addAttachment(fileName, file);
//            mailSender.send(message);
//            //日志信息
//            logger.info("Mail send successfully");
//        } catch (Exception e) {
//            logger.error("Mail send fail!", e);
//        }
//    }
//}
