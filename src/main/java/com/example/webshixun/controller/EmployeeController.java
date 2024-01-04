package com.example.webshixun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webshixun.common.Result;
import com.example.webshixun.dto.req.AddEmployeeReq;
import com.example.webshixun.dto.req.UpdateEmployeeReq;
import com.example.webshixun.entity.Employee;
import com.example.webshixun.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
     *
     * @param
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<Page<Employee>> selectAll(@RequestParam("pagenum") Integer pagenum,
                                            @RequestParam("pagesize") Integer pagesize,
                                            @RequestParam("deptId") String deptId) {
        //分页构造器
        Page<Employee> pageInfo = new Page<>(pagenum, pagesize);
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<Employee>()
                .eq(! "".equals(deptId), Employee::getDeptId,deptId);
        Page<Employee> page = employeeService.page(pageInfo, queryWrapper);
        return Result.success(page, "", page.getTotal());
    }

    /**
     * 新增员工
     *
     * @param addEmployeeReq 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public Result<String> insert(AddEmployeeReq addEmployeeReq) {

        Employee employee = new Employee();
        employee.setNo(addEmployeeReq.getNo());
        employee.setName(addEmployeeReq.getName());
        int i = Integer.parseInt(addEmployeeReq.getDeptId());
        employee.setDeptId(i);
        String sex = addEmployeeReq.getSex();
        if ( sex.equals("0") ) {
            employee.setSex("女");
        }else {
            employee.setSex("男");
        }
        employee.setEntryTime(addEmployeeReq.getEntryTime());
        boolean save = employeeService.save(employee);
        if (!save) {
            return Result.error("添加失败");
        }
        return Result.success("添加成功");
    }

    /**
     * 获取员工基本信息
     * @param id
     * @return
     */
    @GetMapping("/info")
    public Result<Employee> getEmployeeDetail(@RequestParam("id") String id) {
        Employee employee = employeeService.getById(id);

        return Result.success(employee,"");
    }

    /**
     * 修改员工信息
     *
     * @param employee 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public Result<String> update( UpdateEmployeeReq employee) {
        int count = employeeService.count(new LambdaQueryWrapper<Employee>()
                .eq(Employee::getId, employee.getId()));
        if (count == 0) {
            return Result.error("员工不存在");
        }
        Employee employee1 = employeeService.getById(employee.getId());
        employee1.setNo(employee.getNo());
        employee1.setName(employee.getName());
        int i = Integer.parseInt(employee.getDeptId());
        employee1.setDeptId(i);
        String sex = employee.getSex();
        if ( sex.equals("0") ) {
            employee1.setSex("女");
        }else {
            employee1.setSex("男");
        }
        employee1.setEntryTime(employee.getEntryTime());
        boolean b = employeeService.updateById(employee1);
        if (!b) {
            return Result.error("修改失败");
        }
        return Result.success("修改成功");
    }

    /**
     * 删除员工
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public Result<String> delete(@RequestParam("id") Long id) {
        employeeService.removeById(id);
        return Result.success("删除成功");
    }


}

