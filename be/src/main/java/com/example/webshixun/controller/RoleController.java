package com.example.webshixun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.webshixun.common.Result;
import com.example.webshixun.dto.req.InsertRoleReq;
import com.example.webshixun.entity.Menu;
import com.example.webshixun.entity.Permissions;
import com.example.webshixun.entity.Role;
import com.example.webshixun.service.MenuService;
import com.example.webshixun.service.PermissionsService;
import com.example.webshixun.service.RoleService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
     *
     * @param
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<List<Role>> selectAll() {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Role::getCreateTime);
        List<Role> list = roleService.list(queryWrapper);
        return Result.success(list, "");
    }

    /**
     * 新增角色
     *
     * @param role 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public Result<String> insert(@RequestBody InsertRoleReq role, HttpServletRequest request) {

        Role role1 = new Role();
        role1.setId(role.getRoleId());
        role1.setName(role.getName());

        try {
            roleService.save(role1);

            List<Menu> list = menuService.list();
            for (int i = 0; i < list.size(); i++) {
                Permissions permissions = new Permissions();
                permissions.setRoleId(role.getRoleId());
                permissions.setRoleName(role.getName());
                permissions.setMenuId(list.get(i).getId());
                permissions.setMenuName(list.get(i).getName());
                permissions.setUseIt(0);
                permissionsService.save(permissions);
            }
        } catch (Exception e) {
            return Result.error("添加失败");
        }

            return Result.success("添加成功");

    }

    /**
     * 修改角色
     *
     * @param role 实体对象
     * @return 修改结果
     */
    @PutMapping("/info")
    public Result<String> update(@RequestBody Role role) {
        int count = roleService.count(new LambdaQueryWrapper<Role>()
                .eq(Role::getId, role.getId()));
        if (count == 0) {
            return Result.error("角色不存在");
        }
        roleService.updateById(role);
        LambdaQueryWrapper<Permissions> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Permissions::getRoleId,role.getId());
        List<Permissions> list = permissionsService.list(queryWrapper);
        for (Permissions per : list ) {
            per.setRoleName(role.getName());
            permissionsService.updateById(per);
        }
        return Result.success("修改成功");
    }

    /**
     * 删除记录
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/del")
    public Result<String> delete(@RequestParam("id") Long id) {
        roleService.removeById(id);
        LambdaQueryWrapper<Permissions> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Permissions::getRoleId,id);
        List<Permissions> list = permissionsService.list(queryWrapper);
        for (Permissions per : list ) {
            permissionsService.removeById(per);
        }
        return Result.success("删除成功");
    }


}

