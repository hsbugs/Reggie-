package com.itheima.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//启动类

@Slf4j  //启动日志
@SpringBootApplication
@ServletComponentScan //创建 ServletComponentScan 可以扫描到过滤器
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class,args);
        log.info("Run Successfully...");    //Slf4j提供 启动日志
    }
}
