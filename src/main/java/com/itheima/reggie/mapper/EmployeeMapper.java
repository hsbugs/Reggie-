package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//MybatisPlus提供的BaseMapper 放入Employee实体类
public interface EmployeeMapper extends BaseMapper<Employee>{
}
