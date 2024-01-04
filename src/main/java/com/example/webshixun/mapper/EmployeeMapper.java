package com.example.webshixun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import com.example.webshixun.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Employee)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-04 18:28:33
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}

