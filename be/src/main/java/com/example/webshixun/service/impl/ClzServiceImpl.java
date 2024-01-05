package com.example.webshixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webshixun.entity.Clz;
import com.example.webshixun.mapper.ClassMapper;
import com.example.webshixun.service.ClzService;
import org.springframework.stereotype.Service;


@Service("classService")
public class ClzServiceImpl extends ServiceImpl<ClassMapper, Clz> implements ClzService {

}

