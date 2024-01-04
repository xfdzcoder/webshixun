package com.example.webshixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webshixun.entity.Permissions;
import com.example.webshixun.mapper.PermissionsMapper;
import com.example.webshixun.service.PermissionsService;
import org.springframework.stereotype.Service;

/**
 * (Permissions)表服务实现类
 *
 * @author makejava
 * @since 2024-01-04 18:28:34
 */
@Service("permissionsService")
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements PermissionsService {

}

