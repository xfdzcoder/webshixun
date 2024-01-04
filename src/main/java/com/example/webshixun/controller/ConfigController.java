package com.example.webshixun.controller;


import com.example.webshixun.common.Result;
import com.example.webshixun.entity.Config;
import com.example.webshixun.service.ConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("config")
public class ConfigController {
    /**
     * 服务对象
     */
    @Resource
    private ConfigService configService;


    /**
     * 查询所有数据
     *
     * @param
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<List<Config>> selectAll() {
        List<Config> list = configService.list();
        return Result.success(list, "");
    }

}

