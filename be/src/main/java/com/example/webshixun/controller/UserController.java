package com.example.webshixun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.webshixun.common.Result;
import com.example.webshixun.entity.User;
import com.example.webshixun.service.UserService;
import com.example.webshixun.utils.PasswordUtil;
import com.example.webshixun.vo.req.UpdateUserInfoReq;
import com.example.webshixun.vo.req.UpdatepwdReq;
import com.example.webshixun.vo.req.UserInsertReq;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 根据 id 修改邮箱
     */
    @PutMapping("/email")
    public Result<String> updateUserInfo(@RequestBody UpdateUserInfoReq updateUserInfoReq){
        Long id = updateUserInfoReq.getId();
        String email = updateUserInfoReq.getEmail();
        User one = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getId, id));
        one.setEmail(email);
        userService.updateById(one);
        return Result.success();
    }

    /**
     * 更新用户密码
     */
    @PatchMapping("/password")
    public Result<String> updateUserPassword(@RequestBody UpdatepwdReq req, HttpServletRequest request){
        String oldPwd = req.getOldPwd();
        String newPwd = req.getNewPwd();

        User one = userService.getById(req.getId());
        //对新密码进行不可逆加密(MD5+随机盐值)
        newPwd = PasswordUtil.encrypt(newPwd);

        boolean equal = PasswordUtil.valid(oldPwd, one.getPassword());
        if (equal) {
            one.setPassword(newPwd);
            userService.updateById(one);
            return Result.success("密码修改成功");
        }
        return Result.error("密码输入错误");
    }

    /**
     * 查询所有数据
     */
    @GetMapping("/list")
    public Result<List<User>> selectAll() {
        List<User> list = userService.list(new LambdaQueryWrapper<User>()
                .orderByAsc(User::getCreateTime));
        return Result.success(list, "");
    }

    /**
     * 新增用户
     */
    @PostMapping("")
    public Result<String> insert(@RequestBody UserInsertReq userReq) {
        User user = new User();
        user.setAccount(userReq.getAccount());
        String password = PasswordUtil.encrypt(userReq.getPassword());
        user.setPassword(password);
        user.setRoleId(userReq.getRoleId());
        userService.save(user);
        return Result.success();
    }

    /**
     * 修改用户角色ID
     */
    @PutMapping
    public Result<String> update(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }

    /**
     * 删除用户
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        userService.removeById(id);
        return Result.success();
    }

}

