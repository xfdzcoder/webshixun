package com.example.webshixun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.webshixun.common.Result;
import com.example.webshixun.entity.Menu;
import com.example.webshixun.service.MenuService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("menu")
public class MenuController {
    /**
     * 服务对象
     */
    @Resource
    private MenuService menuService;

    /**
     * 查询所有数据
     */
    @GetMapping("/list")
    public Result<List<Menu>> selectAll() {
        List<Menu> list = menuService.list(new LambdaQueryWrapper<Menu>()
                .orderByAsc(Menu::getCreateTime));
        return Result.success(list,"");
    }

}

