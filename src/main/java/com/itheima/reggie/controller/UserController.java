package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.User;
import com.itheima.reggie.service.UserService;
import com.itheima.reggie.utils.SMSUtils;
import com.itheima.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    //发送手机验证码
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        //获取手机号
        String phone = user.getPhone();
        //判断手机号
        if (StringUtils.isNotEmpty(phone)){
            //生成4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}",code);
            //调用阿里发送
//            SMSUtils.sendMessage("瑞吉外卖","",phone,code);
            //保存生成的验证码在Session中
            session.setAttribute(phone,code);
            return R.success("发送成功");

        }
        return R.error("发送失败");
    }


//    //移动端登录
//    @PostMapping("/login")
//    public R<User> login(@RequestBody Map map, HttpSession session){
//        log.info(map.toString());
//
//        //获取手机号
//        String phone = map.get("phone").toString();
//        //获取验证码
//        String code = map.get("code").toString();
//        //获取保存的验证码 进行比对
//        Object codeInSession = session.getAttribute(phone);
//
//        if (codeInSession != null && codeInSession.equals(code)){
//            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//            queryWrapper.eq(User::getPhone,phone);
//
//            User user = userService.getOne(queryWrapper);
//            if (user == null){
//                user = new User();
//                user.setPhone(phone);
//                user.setStatus(1);
//                userService.save(user);
//            }
//            session.setAttribute("user",user.getId());
//            return R.success(user);
//        }
//
//        //登陆成功 判断手机号是否在用户表里
//        return R.error("登录失败");
//    }
}
