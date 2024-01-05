package com.example.webshixun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webshixun.entity.Menu;
import com.example.webshixun.mapper.MenuMapper;
import com.example.webshixun.service.MenuService;
import org.springframework.stereotype.Service;


@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}

