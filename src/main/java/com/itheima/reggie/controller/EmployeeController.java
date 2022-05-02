package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployService employService;

    //后台登录请求地址 http://localhost:8080/employee/login
    /*
    员工登录
     */
    //登录的实现
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //1.将页面提交的代码 md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2.查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employService.getOne(queryWrapper);

        //3.如果没有查到 返回登录失败结果
        if (emp == null){
            return R.error("登录失败");
        }

        //4.如果查到 就进行密码的比对 不一样则返回登录失败的结果
        if (!emp.getPassword().equals(password)) {
            return R.error("登录失败");
        }

        //5.查看员工的登录状态 如果为已禁用 则返回已禁用结果
        if (emp.getStatus() == 0){
            return R.error("该账号已禁用");
        }

        //6.登陆成功 将员工id存入Session并返回登陆成功结果
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    //退出实现
    //请求路径: http://localhost:8080/employee/logout
    @PostMapping("logout")
    public R<String> logout(HttpServletRequest request){
        //清理Session中保存的员工登录id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }


    //请求路径:  http://localhost:8080/employee/
    //新增员工
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工信息: {}",employee.toString());

        //设置默认密码123456 md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //设置添加时的时间
//        employee.setCreateTime(LocalDateTime.now());
        //设置更新时间
//        employee.setUpdateTime(LocalDateTime.now());
        //设置创建人id   (当前登录用户的id)
//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);

        employService.save(employee);
        return R.success("添加成功");
    }

    //员工信息分页查询 请求地址:  http://localhost:8080/employee/page?page=1&pageSize=10
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("page = {},pageSize = {}, name = {}",page,pageSize,name);

        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询
        employService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    //根据id修改员工信息
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        log.info(employee.toString());

//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateUser(empId);
//        employee.setUpdateTime(LocalDateTime.now());
        employService.updateById(employee);
        return R.success("员工信息修改成功");
    }

    //请求地址: http://localhost:8080/employee/1521056061543436290
    //根据id修改员工信息
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息..");
        Employee employee = employService.getById(id);
        if (employee != null){
            return R.success(employee);
        }
        return R.error("未查询到..");
    }
}
