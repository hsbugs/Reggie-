package com.itheima.reggie.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.mapper.EmployeeMapper;
import com.itheima.reggie.service.EmployService;
import org.springframework.stereotype.Service;

//实现类
@Service
//MybatisPlus提供的 继承父类 实现父接口
public class EmployServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployService {
}
