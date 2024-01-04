package com.example.webshixun.controller;


import com.example.webshixun.common.Result;
import com.example.webshixun.entity.Dept;
import com.example.webshixun.service.DeptService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    /**
     * 服务对象
     */
    @Resource
    private DeptService deptService;


    /**
     * 查询所有数据
     */
    @GetMapping("/list")
    public Result<List<Dept>> selectAll() {
        List<Dept> list = deptService.list();
        return Result.success(list, "");
    }

    /**
     * 新增部门
     */
    @PostMapping
    public Result<String> insert(@RequestBody Dept dept) {
        deptService.save(dept);
        return Result.success();
    }

    /**
     * 修改部门信息
     */
    @PutMapping
    public Result<String> update(@RequestBody Dept dept) {
        deptService.updateById(dept);
        return Result.success();
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        deptService.removeById(id);
        return Result.success();
    }

}