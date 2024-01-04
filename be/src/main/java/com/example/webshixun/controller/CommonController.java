package com.example.webshixun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.webshixun.common.Result;
import com.example.webshixun.dto.resp.LoginResp;
import com.example.webshixun.dto.resp.MenuResp;
import com.example.webshixun.entity.Menu;
import com.example.webshixun.entity.Permissions;
import com.example.webshixun.entity.Student;
import com.example.webshixun.entity.User;
import com.example.webshixun.service.*;
import com.example.webshixun.utils.PasswordUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

// TODO
@RestController
@RequestMapping("common")
public class CommonController {

    @Resource
    private UserService userService;

    @Resource
    private StudentService studentService;

    @Resource
    private PermissionsService permissionsService;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleService roleService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user){
        //注册的用户名
        String userAccount = user.getAccount();
        //注册的密码
        String password = user.getPassword();
        //注册时选择的角色
        Integer roleId = user.getRoleId();

        //对注册的密码进行不可逆加密(MD5+随机盐值)
        password = PasswordUtil.encrypt(password);

        //根据页面提交的用户名查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getAccount,userAccount);
        User one = userService.getOne(queryWrapper);

        //如果没有查询到结果则进行注册
        if ( one == null ) {
            user.setPassword(password);
            userService.save(user);
            Student student = new Student();
            student.setNo(userAccount);
            studentService.save(student);

            return Result.success("注册成功！！！");
        }

        return Result.error("用户名已存在！！！");
    }


    /**
     * 用户登录
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result<LoginResp> login(@RequestBody User user, HttpServletRequest request) {
        //获取页面提交的用户名和密码
        String userAccount = user.getAccount();
        String password = user.getPassword();

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getAccount,userAccount);
        User one = userService.getOne(queryWrapper);

        if ( one == null ) {
            return Result.error("用户名不存在，请先注册");
        }

        if (one.getStatus() == 0) {
            return Result.error("密码错误5次，账户已锁定");
        }
        Integer error = one.getError();
        if (error >= 5 ) {
            one.setStatus(0);
            userService.updateById(one);
            return Result.error("密码错误5次，账户已锁定");
        }else {
            boolean b = PasswordUtil.valid(password, one.getPassword());
            if ( !b ) {
                one.setError(one.getError()+1);
                userService.updateById(one);
                return Result.error("密码错误");
            }
        }

        Integer roleId = one.getRoleId();
        LambdaQueryWrapper<Permissions> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Permissions::getRoleId,roleId)
                .eq(Permissions::getUseIt,1);
        List<Permissions> list = permissionsService.list(queryWrapper1);
        List<Long> menuIds = new ArrayList<Long>();
        for (Permissions a : list) {
            menuIds.add(a.getMenuId());
        }
        MenuResp menuResp = new MenuResp();
        for (Long menuId : menuIds){
            Menu menu = menuService.getById(menuId);
            String hrefUrl = menu.getHrefUrl();
            if ( hrefUrl.equals("/dept")) {
                menuResp.setDept("1");
            }
            if ( hrefUrl.equals("/employee")) {
                menuResp.setEmployee("1");
            }
            if ( hrefUrl.equals("/userManager")) {
                menuResp.setUserManager("1");
            }
            if (hrefUrl.equals("/holiday")) {
                menuResp.setHoliday("1");
            }
            if (hrefUrl.equals("/reimburse")) {
                menuResp.setReimburse("1");
            }
            if (hrefUrl.equals("/user/email")) {
                menuResp.setEmail("1");
            }
            if (hrefUrl.equals("/role")) {
                menuResp.setRole("1");
            }
            if (hrefUrl.equals("/permission")) {
                menuResp.setPermission("1");
            }
            if (hrefUrl.equals("/user/password")) {
                menuResp.setPassword("1");
            }
            if (hrefUrl.equals("/user/avatar")) {
                menuResp.setAvatar("1");
            }
            if (hrefUrl.equals("/clz")) {
                menuResp.setClz("1");
            }
            if (hrefUrl.equals("/student")) {
                menuResp.setStudent("1");
            }
        }

//        one.setError(0);
//        userService.updateById(one);
//
//        Role role = roleService.getById(one.getRoleId());
//
//        return Result.success(new LoginResp(one, tokenInfo.tokenValue, menuResp, role.getName()), "登录成功");
        return null;
    }

    /**
     * 获取用户基本信息
     * @return
     */
//    @NeedAuth(needRole = {"admin"})
    @GetMapping("/userinfo")
    public Result<User> getUserInfo(HttpServletRequest request){
//        //从拦截器拦截到的请求头里拿token
//        String authorization = request.getHeader("Authorization");
//        //获取token里面的数据
//        String account = TokenUtil.getDecode(authorization);
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getAccount,account);
//        User user = userService.getOne(queryWrapper);
//
//        return Result.success(user,"");
        // 获取当前会话账号id, 如果未登录，则抛出异常：`NotLoginException`
//        Object loginId = StpUtil.getLoginId();
//        Long userId = Long.valueOf(String.valueOf(loginId));
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getId,userId);
//        User user = userService.getOne(queryWrapper);
//
//        return Result.success(user,"");
        return null;
    }

    /**
     * 获取权限信息
     * @return
     */
//    @NeedAuth(needRole = {"admin"})
    @GetMapping("/getMenu")
    public Result<MenuResp> getMenu(HttpServletRequest request){

//        // 获取当前会话账号id, 如果未登录，则抛出异常：`NotLoginException`
//        Object loginId = StpUtil.getLoginId();
//        Long userId = Long.valueOf(String.valueOf(loginId));
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getId,userId);
//        User user = userService.getOne(queryWrapper);
//
//        Integer roleId = user.getRoleId();
//        LambdaQueryWrapper<Permissions> queryWrapper1 = new LambdaQueryWrapper<>();
//        queryWrapper1.eq(Permissions::getRoleId,roleId)
//                .eq(Permissions::getUseIt,1);
//        List<Permissions> list = permissionsService.list(queryWrapper1);
//        List<Long> menuIds = new ArrayList<Long>();
//        for (Permissions a : list) {
//            menuIds.add(a.getMenuId());
//        }
//        MenuResp menuResp = new MenuResp();
//        for (Long menuId : menuIds){
//            Menu menu = menuService.getById(menuId);
//            String hrefUrl = menu.getHrefUrl();
//            if ( hrefUrl.equals("/dept")) {
//                menuResp.setDept("1");
//            }
//            if ( hrefUrl.equals("/employee")) {
//                menuResp.setEmployee("1");
//            }
//            if ( hrefUrl.equals("/userManager")) {
//                menuResp.setUserManager("1");
//            }
//            if (hrefUrl.equals("/holiday")) {
//                menuResp.setHoliday("1");
//            }
//            if (hrefUrl.equals("/reimburse")) {
//                menuResp.setReimburse("1");
//            }
//            if (hrefUrl.equals("/user/email")) {
//                menuResp.setEmail("1");
//            }
//            if (hrefUrl.equals("/role")) {
//                menuResp.setRole("1");
//            }
//            if (hrefUrl.equals("/permission")) {
//                menuResp.setPermission("1");
//            }
//            if (hrefUrl.equals("/user/password")) {
//                menuResp.setPassword("1");
//            }
//            if (hrefUrl.equals("/user/avatar")) {
//                menuResp.setAvatar("1");
//            }
//            if (hrefUrl.equals("/user/avatar")) {
//                menuResp.setAvatar("1");
//            }
//            if (hrefUrl.equals("/clz")) {
//                menuResp.setClz("1");
//            }
//            if (hrefUrl.equals("/student")) {
//                menuResp.setStudent("1");
//            }
//        }

//        return Result.success(menuResp,"");
        return null;
    }


}
