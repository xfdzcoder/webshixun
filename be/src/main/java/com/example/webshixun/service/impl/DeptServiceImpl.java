package com.example.webshixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webshixun.entity.Dept;
import com.example.webshixun.mapper.DeptMapper;
import com.example.webshixun.service.DeptService;
import org.springframework.stereotype.Service;


@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

}

