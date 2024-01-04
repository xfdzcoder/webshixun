package com.example.webshixun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.webshixun.common.Result;
import com.example.webshixun.entity.Student;
import com.example.webshixun.entity.User;
import com.example.webshixun.service.StudentService;
import com.example.webshixun.service.UserService;
import com.example.webshixun.utils.PasswordUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("common")
public class CommonController {

    @Resource
    private UserService userService;

    @Resource
    private StudentService studentService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user){
        String userAccount = user.getAccount();
        String password = user.getPassword();
        password = PasswordUtil.encrypt(password);
        User one = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getAccount, userAccount));
        if (one == null) {
            user.setPassword(password);
            userService.save(user);
            Student student = new Student();
            student.setNo(userAccount);
            studentService.save(student);
            return Result.success();
        }
        return Result.error("用户名已存在！");
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody User req) {
        String userAccount = req.getAccount();
        String password = req.getPassword();

        User user = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getAccount, userAccount));

        if (user == null) {
            return Result.error("用户名不存在，请先注册");
        }

        if (user.getStatus() == 0) {
            return Result.error("密码错误5次，账户已锁定");
        }
        Integer error = user.getError();
        if (error >= 5 ) {
            user.setStatus(0);
            userService.updateById(user);
            return Result.error("密码错误5次，账户已锁定");
        } else {
            if (!PasswordUtil.valid(password, user.getPassword())) {
                user.setError(user.getError() + 1);
                userService.updateById(user);
                return Result.error("密码错误");
            }
        }
        return Result.success(user, "登录成功");
    }

    @GetMapping("userinfo/{id}")
    public Result<User> userinfo(@Param("id") Long id){
        User byId = userService.getById(id);
        return Result.success(byId,"获取信息成功！");
    }
}
