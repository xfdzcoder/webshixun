package com.example.webshixun.controller;


import com.example.webshixun.common.Result;
import com.example.webshixun.entity.Student;
import com.example.webshixun.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/list")
    public Result<List<Student>> list() {
        List<Student> list = studentService.list();
        return Result.success(list, "");
    }

    @PostMapping
    public Result<String> save(@RequestBody Student student) {
        studentService.save(student);
        return Result.success();
    }

    @PutMapping
    public Result<String> update(@RequestBody Student student) {
        studentService.updateById(student);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        studentService.removeById(id);
        return Result.success("删除成功");
    }
}
