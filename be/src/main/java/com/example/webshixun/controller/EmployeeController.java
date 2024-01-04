package com.example.webshixun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.webshixun.common.Result;
import com.example.webshixun.entity.Employee;
import com.example.webshixun.service.EmployeeService;
import com.example.webshixun.vo.req.EmployeeInsertReq;
import com.example.webshixun.vo.req.EmployeeUpdateReq;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("employee")
public class EmployeeController {

    /**
     * 服务对象
     */
    @Resource
    private EmployeeService employeeService;

    /**
     * 查询所有数据
     */
    @GetMapping("/list")
    public Result<List<Employee>> selectAll(@RequestParam("deptId") Integer deptId) {
        List<Employee> list = employeeService.list(new LambdaQueryWrapper<Employee>()
                .eq(Employee::getDeptId, deptId));
        return Result.success(list, "");
    }

    /**
     * 新增员工
     */
    @PostMapping
    public Result<String> insert(@RequestBody EmployeeInsertReq req) {
        Employee employee = new Employee();
        employee.setNo(req.getNo());
        employee.setName(req.getName());
        int i = Integer.parseInt(req.getDeptId());
        employee.setDeptId(i);
        employee.setSex(req.getSex().equals("0") ? "女" : "男");
        employee.setEntryTime(req.getEntryTime());
        employeeService.save(employee);
        return Result.success();
    }

    /**
     * 获取员工基本信息
     */
    @GetMapping("/{id}")
    public Result<Employee> getEmployeeDetail(@PathVariable("id") Long id) {
        Employee employee = employeeService.getById(id);
        return Result.success(employee,"");
    }

    /**
     * 修改员工信息
     */
    @PutMapping
    public Result<String> update(@RequestBody EmployeeUpdateReq req) {
        Employee employee = employeeService.getById(req.getId());
        employee.setNo(req.getNo());
        employee.setName(req.getName());
        int i = Integer.parseInt(req.getDeptId());
        employee.setDeptId(i);
        employee.setSex(req.getSex().equals("0") ? "女" : "男");
        employee.setEntryTime(req.getEntryTime());
        employeeService.updateById(employee);
        return Result.success();
    }

    /**
     * 删除员工
     */
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        employeeService.removeById(id);
        return Result.success("删除成功");
    }


}

