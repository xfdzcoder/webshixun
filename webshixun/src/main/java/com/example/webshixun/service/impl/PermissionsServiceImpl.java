package com.example.webshixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webshixun.entity.Permissions;
import com.example.webshixun.mapper.PermissionsMapper;
import com.example.webshixun.service.PermissionsService;
import org.springframework.stereotype.Service;


@Service("permissionsService")
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements PermissionsService {

}

