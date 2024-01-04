package com.example.webshixun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webshixun.common.Result;
import com.example.webshixun.dto.req.AddPermissionReq;
import com.example.webshixun.entity.Permissions;
import com.example.webshixun.service.MenuService;
import com.example.webshixun.service.PermissionsService;
import com.example.webshixun.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("permissions")
public class PermissionsController {
    /**
     * 服务对象
     */
    @Resource
    private PermissionsService permissionsService;

    @Resource
    private RoleService roleService;

    @Resource
    private MenuService menuService;

    /**
     * 获取权限列表
     * @param
     * @return
     */
    @GetMapping("/list")
    public Result<Page<Permissions>> listArticle(@RequestParam("pagenum") Integer pagenum,
                                                 @RequestParam("pagesize") Integer pagesize,
                                                 @RequestParam("roleId") String roleId
                                                 ) {

        //分页构造器
        Page<Permissions> pageInfo = new Page<>(pagenum, pagesize);
        LambdaQueryWrapper<Permissions> queryWrapper = new LambdaQueryWrapper<Permissions>()
                .eq(! "".equals(roleId), Permissions::getRoleId,roleId)
                .eq(Permissions::getUseIt,1);
        Page<Permissions> page = permissionsService.page(pageInfo, queryWrapper);
        return Result.success(page, "", page.getTotal());


    }

    /**
     * 获取权限详情
     * @param id
     * @return
     */
    @GetMapping("/info")
    public Result<Permissions> getArticleDetail(@RequestParam("id") String id) {
        Permissions permission = permissionsService.getById(id);

        return Result.success(permission,"");
    }

    /**
     * 添加权限
     * @param
     * @return
     * formData对象直接使用普通类对象接收
     * @RequestBody 接收前端发来的json对象
     * @RequestPart 接收并解构前端发来的json对象
     */
    @PostMapping("/add")
    public Result<String> artPublish(AddPermissionReq permissionReq) {
        LambdaQueryWrapper<Permissions> queryWrapper = new LambdaQueryWrapper<Permissions>();
        queryWrapper.eq(Permissions::getRoleId,permissionReq.getRoleId())
                .eq(Permissions::getMenuId,permissionReq.getMenuId());
        Permissions one = permissionsService.getOne(queryWrapper);
        one.setUseIt(1);
        boolean b = permissionsService.updateById(one);

        if ( b ) {
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }

    /**
     * 删除权限
     * @return
     */
    @DeleteMapping("/delete")
    public Result<String> deleteArticle(@RequestParam("id") String id) {
        Permissions one = permissionsService.getById(id);
        one.setUseIt(0);
        boolean b = permissionsService.updateById(one);
        if ( b == true ) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }


}

