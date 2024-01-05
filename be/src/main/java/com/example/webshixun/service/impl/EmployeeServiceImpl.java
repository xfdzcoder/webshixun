package com.example.webshixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webshixun.entity.Employee;
import com.example.webshixun.mapper.EmployeeMapper;
import com.example.webshixun.service.EmployeeService;
import org.springframework.stereotype.Service;


@Service("employeeService")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}

