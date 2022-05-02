package com.itheima.reggie.controller;


import com.itheima.reggie.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;
    //文件上传
    @PostMapping("/upload")
    //file是临时文件 需要转存到指定位置
    public R<String> upload(MultipartFile file){
        //MultipartFile  Spring提供的上传文件  file要和前端界面参数一致

        //原始文件名
        String originalFilename = file.getOriginalFilename();
        String ss = originalFilename.substring(originalFilename.lastIndexOf("."));
        //使用uuid重新生成文件名称 防止文件名重复造成文件覆盖
        String filename = UUID.randomUUID().toString() + ss;

        //创建目录对象
        File dir = new File(basePath);
        //判断目录是否存在
        if (!dir.exists()){
            //不存在 创建
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(basePath + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(filename);
    }

    //文件下载
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        //输入流 读内容
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

            //输出流 写回浏览器
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];

            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            outputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
