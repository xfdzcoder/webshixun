package com.example.webshixun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.webshixun.common.Result;
import com.example.webshixun.entity.Dept;
import com.example.webshixun.service.DeptService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("dept")
public class DeptController {
    /**
     * 服务对象
     */
    @Resource
    private DeptService deptService;


    /**
     * 查询所有数据
     *
     * @param
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<List<Dept>> selectAll() {
        List<Dept> list = deptService.list();
        return Result.success(list, "");
    }

    /**
     * 新增部门
     *
     * @param dept 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public Result<String> insert(@RequestBody Dept dept) {

//        Dept dept1 = new Dept();
//        dept1.setNo(dept.getNo());
//        dept1.setName(dept.getName());
//        dept1.setAddress(dept.getAddress());
//        dept1.setUser(dept.getUser());

        try {
            deptService.save(dept);

        } catch (Exception e) {
            return Result.error("添加失败");
        }

        return Result.success("添加成功");

    }

    /**
     * 修改部门信息
     *
     * @param dept 实体对象
     * @return 修改结果
     */
    @PutMapping("/info")
    public Result<String> update(@RequestBody Dept dept) {
        int count = deptService.count(new LambdaQueryWrapper<Dept>()
                .eq(Dept::getId, dept.getId()));
        if (count == 0) {
            return Result.error("部门不存在");
        }
        deptService.updateById(dept);

        return Result.success("修改成功");
    }

    /**
     * 删除用户
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/del")
    public Result<String> delete(@RequestParam("id") Long id) {
        deptService.removeById(id);
        return Result.success("删除成功");
    }

}