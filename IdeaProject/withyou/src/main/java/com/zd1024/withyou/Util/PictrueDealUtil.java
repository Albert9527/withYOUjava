package com.zd1024.withyou.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class PictrueDealUtil {

    public static String savePictrue(MultipartFile pic) {

        int Error = 1;

        if (pic.isEmpty()) {
            Error = 404;
            return null;
        } else {
            String fileName = pic.getOriginalFilename();//获取文件名

            //获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            //指定图片上传到哪个文件夹的路径
           // String filepath="D:/tempFiles/files/";//本地路径
            String filepath="/root/img/";//服务器路径

           // String filepath = "/opt/MeetStoneProject/target/img/";
            // Thread.currentThread().getContextClassLoader().getResource("").getPath()+"userimg/";

            fileName = UUID.randomUUID() + suffixName;//重新命名图片，变成随机的名字
            File dest = new File(filepath + fileName);//在上传的文件夹处创建文件
            try {
                pic.transferTo(dest);
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
