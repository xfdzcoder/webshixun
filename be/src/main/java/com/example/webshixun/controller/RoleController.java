package com.example.webshixun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.webshixun.common.Result;
import com.example.webshixun.entity.Menu;
import com.example.webshixun.entity.Permissions;
import com.example.webshixun.entity.Role;
import com.example.webshixun.service.MenuService;
import com.example.webshixun.service.PermissionsService;
import com.example.webshixun.service.RoleService;
import com.example.webshixun.vo.req.RoleInsertReq;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    @Resource
    private PermissionsService permissionsService;

    @Resource
    private MenuService menuService;

    /**
     * 查询所有数据
     */
    @GetMapping("/list")
    public Result<List<Role>> selectAll() {
        List<Role> list = roleService.list(new LambdaQueryWrapper<Role>().orderByAsc(Role::getCreateTime));
        return Result.success(list, "");
    }

    /**
     * 新增角色
     */
    @PostMapping
    public Result<String> insert(@RequestBody RoleInsertReq role) {
        Role role1 = new Role();
        role1.setId(role.getRoleId());
        role1.setName(role.getName());
        roleService.save(role1);
        List<Menu> list = menuService.list();
        for (Menu menu : list) {
            Permissions permissions = new Permissions();
            permissions.setRoleId(role.getRoleId());
            permissions.setRoleName(role.getName());
            permissions.setMenuId(menu.getId());
            permissions.setMenuName(menu.getName());
            permissions.setUseIt(0);
            permissionsService.save(permissions);
        }
        return Result.success();
    }

    /**
     * 修改角色
     */
    @PutMapping
    public Result<String> update(@RequestBody Role role) {
        roleService.updateById(role);
        LambdaQueryWrapper<Permissions> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Permissions::getRoleId,role.getId());
        List<Permissions> list = permissionsService.list(queryWrapper);
        for (Permissions per : list ) {
            per.setRoleName(role.getName());
            permissionsService.updateById(per);
        }
        return Result.success();
    }

    /**
     * 删除记录
     */
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        roleService.removeById(id);
        LambdaQueryWrapper<Permissions> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Permissions::getRoleId,id);
        List<Permissions> list = permissionsService.list(queryWrapper);
        for (Permissions per : list ) {
            permissionsService.removeById(per);
        }
        return Result.success();
    }
}

