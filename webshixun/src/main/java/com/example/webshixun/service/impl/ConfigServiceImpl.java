package com.example.webshixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webshixun.entity.Config;
import com.example.webshixun.mapper.ConfigMapper;
import com.example.webshixun.service.ConfigService;
import org.springframework.stereotype.Service;


@Service("configService")
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

}

