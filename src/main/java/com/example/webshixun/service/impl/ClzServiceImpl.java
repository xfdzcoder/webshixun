package com.example.webshixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webshixun.entity.Clz;
import com.example.webshixun.mapper.ClassMapper;
import org.springframework.stereotype.Service;
import com.example.webshixun.service.ClzService;

/**
 * (Class)表服务实现类
 *
 * @author makejava
 * @since 2024-01-04 18:28:32
 */
@Service("classService")
public class ClzServiceImpl extends ServiceImpl<ClassMapper, Clz> implements ClzService {

}

