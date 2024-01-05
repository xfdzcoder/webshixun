package com.example.webshixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webshixun.entity.Student;
import com.example.webshixun.mapper.StudentMapper;
import com.example.webshixun.service.StudentService;
import org.springframework.stereotype.Service;


@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}

