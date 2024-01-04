package com.example.webshixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webshixun.entity.Dept;
import com.example.webshixun.mapper.DeptMapper;
import com.example.webshixun.service.DeptService;
import org.springframework.stereotype.Service;

/**
 * (Dept)表服务实现类
 *
 * @author makejava
 * @since 2024-01-04 18:28:33
 */
@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

}

