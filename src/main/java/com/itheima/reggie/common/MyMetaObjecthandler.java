package com.itheima.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


//自定义的元数据对象处理器
@Component
@Slf4j
public class MyMetaObjecthandler implements MetaObjectHandler {
    //插入自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共自动填充[Insert]...");
        log.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser",  BaseContext.getCurrentId());
        metaObject.setValue("updateUser",  BaseContext.getCurrentId());
    }
    //更新/修改自动填充
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共自动填充[Update]...");
        log.info(metaObject.toString());

        long id = Thread.currentThread().getId();
        log.info("线程id为: {}",id);

        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }
}
