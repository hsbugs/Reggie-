package com.itheima.reggie.config;

import com.itheima.reggie.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration  //Configuration 说明这是配置类
public class WebMvcConfig extends WebMvcConfigurationSupport {
    //默认情况下 访问不到backend 和 front下的加界面  要创建配置类


    //继承WebMvcConfigurationSupport后 要重写里边的addResourceHandlers方法
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        log.info("启动静态资源映射");
        //这个方法的作用是设置静态资源映射
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
        //classpath 相当于路径中的resources
    }

    //扩展mvc框架的消息转换器
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息转化器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器 底层使用Jackson转换为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上边的消息转换器 追加到mvc框架的集合中
        converters.add(0,messageConverter);
    }
}
