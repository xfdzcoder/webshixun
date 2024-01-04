package com.example.webshixun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.webshixun.common.Result;
import com.example.webshixun.entity.Permissions;
import com.example.webshixun.service.PermissionsService;
import com.example.webshixun.vo.req.AddPermissionReq;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("permissions")
public class PermissionsController {
    /**
     * 服务对象
     */
    @Resource
    private PermissionsService permissionsService;

    /**
     * 获取权限列表
     */
    @GetMapping("/list")
    public Result<List<Permissions>> listArticle(@RequestParam("roleId") String roleId) {
        List<Permissions> list = permissionsService.list(new LambdaQueryWrapper<Permissions>());
        return Result.success(list, "");
    }

    /**
     * 获取权限详情
     */
    @GetMapping("/{id}")
    public Result<Permissions> getArticleDetail(@PathVariable("id") Long id) {
        Permissions permission = permissionsService.getById(id);
        return Result.success(permission,"");
    }

    /**
     * 添加权限
     */
    @PostMapping
    public Result<String> artPublish(@RequestBody AddPermissionReq permissionReq) {
        Permissions one = permissionsService.getOne(new LambdaQueryWrapper<Permissions>()
                .eq(Permissions::getRoleId, permissionReq.getRoleId())
                .eq(Permissions::getMenuId, permissionReq.getMenuId()));
        one.setUseIt(1);
        permissionsService.updateById(one);
        return Result.success();
    }

    /**
     * 删除权限
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteArticle(@PathVariable("id") String id) {
        Permissions one = permissionsService.getById(id);
        one.setUseIt(0);
        permissionsService.updateById(one);
        permissionsService.removeById(one.getId());
        return Result.success();
    }
}

