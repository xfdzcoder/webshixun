package com.example.webshixun.controller;


import com.example.webshixun.common.Result;
import com.example.webshixun.entity.Clz;
import com.example.webshixun.service.ClzService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/clz")
public class ClzController {

    /**
     * 服务对象
     */
    @Resource
    private ClzService clzService;

    @PostMapping("/list")
    public Result<List<Clz>> list() {
        List<Clz> list = clzService.list();
        return Result.success(list, "");
    }

    @PostMapping
    public Result<String> save(@RequestBody Clz clz) {
        clzService.save(clz);
        return Result.success();
    }

    @PutMapping
    public Result<String> update(@RequestBody Clz clz) {
        clzService.updateById(clz);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        clzService.removeById(id);
        return Result.success();
    }
}

