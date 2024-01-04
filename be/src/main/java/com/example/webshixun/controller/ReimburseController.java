package com.example.webshixun.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webshixun.common.Result;
import com.example.webshixun.dto.condition.ReimburseCondition;
import com.example.webshixun.entity.Reimburse;
import com.example.webshixun.entity.User;
import com.example.webshixun.service.ReimburseService;
import com.example.webshixun.service.RoleService;
import com.example.webshixun.service.UserService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/reimburse")
public class ReimburseController {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private ReimburseService reimburseService;

    @PostMapping("/list")
    public Result<Page<Reimburse>> list(@RequestBody ReimburseCondition condition) {
//        Long userId = StpUtil.getLoginIdAsLong();
//        User user = userService.getById(userId);
//        Role role = roleService.getById(user.getRoleId());
//        LambdaQueryWrapper<Reimburse> queryWrapper = condition.getLambdaWrapper();
//        if ("student".equals(role.getName()) || "teacher2".equals(role.getName())) {
//            queryWrapper.eq(Reimburse::getUserNo, user.getAccount());
//        }
//        Page<Reimburse> page = reimburseService.page(condition.getPage(), queryWrapper);
//        return Result.success(page, "查询成功");
        // TODO
        return null;
    }

    @PostMapping
    public Result<String> save(@RequestBody Reimburse reimburse) {
        reimburse.setId(null);
        long id = StpUtil.getLoginIdAsLong();
        User user = userService.getById(id);
        reimburse.setUserNo(user.getAccount());
        boolean saved = reimburseService.save(reimburse);
        if (!saved) {
            return Result.error("添加失败");
        }
        return Result.success("添加成功");
    }

    @PutMapping
    public Result<String> update(@RequestBody Reimburse reimburse) {
        if (reimburse.getId() == null) {
            return Result.error("该报销记录不存在");
        }
        reimburse.setUserNo(null);
        boolean updated = reimburseService.updateById(reimburse);
        if (!updated) {
            return Result.error("更新失败");
        }
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.error("该报销记录不存在");
        }
        reimburseService.removeById(id);
        return Result.success("删除成功");
    }
}
