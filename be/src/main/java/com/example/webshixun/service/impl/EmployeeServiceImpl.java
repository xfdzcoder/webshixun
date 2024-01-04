package com.example.webshixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webshixun.entity.Employee;
import com.example.webshixun.mapper.EmployeeMapper;
import com.example.webshixun.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * (Employee)表服务实现类
 *
 * @author makejava
 * @since 2024-01-04 18:28:33
 */
@Service("employeeService")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}

