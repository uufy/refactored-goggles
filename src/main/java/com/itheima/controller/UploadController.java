package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {


    /**
     * 本地磁盘存储方案
     *
     */
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("接受参数为{} {} {}",name,age,file);
//        //保存文件
//        String originalFilename=file.getOriginalFilename();
//
//        String extension=originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newFilename= UUID.randomUUID().toString()+extension;
//        file.transferTo(new File("D:/images/" + newFilename));
//
//        return Result.success();
//    }

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传{} ",file.getOriginalFilename());
        //将文件放到OSS存储
        String url=aliyunOSSOperator.upload(file.getBytes(),file.getOriginalFilename());
        log.info("文件上传OSS,url{} ",url);
        return  Result.success(url);

    }




}
