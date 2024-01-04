package com.example.webshixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webshixun.entity.Role;
import com.example.webshixun.mapper.RoleMapper;
import com.example.webshixun.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2024-01-04 18:28:34
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

