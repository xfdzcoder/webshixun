package com.example.webshixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webshixun.mapper.ConfigMapper;
import com.example.webshixun.entity.Config;
import com.example.webshixun.service.ConfigService;
import com.example.webshixun.mapper.ConfigMapper;
import org.springframework.stereotype.Service;

/**
 * (Config)表服务实现类
 *
 * @author makejava
 * @since 2024-01-04 18:28:33
 */
@Service("configService")
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

}

