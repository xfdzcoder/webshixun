package com.example.webshixun.controller;


import com.example.webshixun.common.Result;
import com.example.webshixun.entity.Config;
import com.example.webshixun.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/config")
public class ConfigController {

    /**
     * 服务对象
     */
    @Autowired
    private ConfigService configService;


    /**
     * 查询所有数据
     */
    @GetMapping("/list")
    public Result<List<Config>> selectAll() {
        List<Config> list = configService.list();
        return Result.success(list, "");
    }

}

