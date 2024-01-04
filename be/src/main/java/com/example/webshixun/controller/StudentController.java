package com.example.webshixun.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webshixun.common.Result;
import com.example.webshixun.dto.condition.StudentCondition;
import com.example.webshixun.entity.Student;
import com.example.webshixun.service.StudentService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/list")
    public Result<Page<Student>> list(@RequestBody StudentCondition condition) {
        Page<Student> page = studentService.page(condition.getPage(), condition.getLambdaWrapper());
        return Result.success(page, "查询成功");
    }

    @PostMapping
    public Result<String> save(@RequestBody Student student) {
        student.setId(null);
        boolean saved = studentService.save(student);
        if (!saved) {
            return Result.error("添加失败");
        }
        return Result.success("添加成功");
    }

    @PutMapping
    public Result<String> update(@RequestBody Student student) {
        if (student.getId() == null) {
            return Result.error("该学生不存在");
        }
        boolean updated = studentService.updateById(student);
        if (!updated) {
            return Result.error("更新失败");
        }
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.error("该学生不存在");
        }
        studentService.removeById(id);
        return Result.success("删除成功");
    }
}
