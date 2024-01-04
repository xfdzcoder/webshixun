package com.example.webshixun.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webshixun.common.Result;
import com.example.webshixun.dto.condition.ClzCondition;
import com.example.webshixun.entity.Clz;
import com.example.webshixun.service.ClzService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/clz")
public class ClzController {
    /**
     * 服务对象
     */
    @Resource
    private ClzService clzService;

    @PostMapping("/list")
    public Result<Page<Clz>> list(@RequestBody ClzCondition condition) {
        Page<Clz> page = clzService.page(condition.getPage(), condition.getLambdaWrapper());
        return Result.success(page, "查询成功");
    }

    @PostMapping
    public Result<String> save(@RequestBody Clz clz) {
        clz.setId(null);
        boolean saved = clzService.save(clz);
        if (!saved) {
            return Result.error("添加失败");
        }
        return Result.success("添加成功");
    }

    @PutMapping
    public Result<String> update(@RequestBody Clz clz) {
        if (clz.getId() == null) {
            return Result.error("该班级不存在");
        }
        boolean updated = clzService.updateById(clz);
        if (!updated) {
            return Result.error("更新失败");
        }
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.error("该班级不存在");
        }
        clzService.removeById(id);
        return Result.success("删除成功");
    }
}

