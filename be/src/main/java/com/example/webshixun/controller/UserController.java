package com.example.webshixun.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.webshixun.common.Result;
import com.example.webshixun.dto.req.AvatarReq;
import com.example.webshixun.dto.req.InsertUserReq;
import com.example.webshixun.dto.req.UpdateUserInfoReq;
import com.example.webshixun.dto.req.UpdatepwdReq;
import com.example.webshixun.entity.User;
import com.example.webshixun.service.StudentService;
import com.example.webshixun.service.UserService;
import com.example.webshixun.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Value;

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

    @Resource
    private StudentService studentService;

    // 资源的 访问 URL
    @Value("${app.minio.base-url}")
    private String baseUrl;

    // API 端点
    @Value("${app.minio.endpoint}")
    private String endpoint;

    // Bucket 存储桶
    @Value("${app.minio.bucket}")
    private String bucket;

    // Acess Key
    @Value("${app.minio.access-key}")
    private String accessKey;

    // Secret Key
    @Value("${app.minio.secret-key}")
    private String secretKey;



    /**
     * 根据id修改邮箱
     * @param updateUserInfoReq
     * @return
     */
//    @NeedAuth(needRole = {"admin"})
    @PutMapping("/email")
    public Result<String> updateUserInfo(@RequestBody UpdateUserInfoReq updateUserInfoReq){
        Long id = updateUserInfoReq.getId();
        String email = updateUserInfoReq.getEmail();

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getId,id);
        User one = userService.getOne(queryWrapper);
        if ( one != null ) {
            one.setEmail(email);
            userService.updateById(one);
        }

        return Result.success("修改成功");
    }

    /**
     * 上传用户头像
     * @param avatarReq
     * @param request
     * @return
     */
//    @NeedAuth(needRole = {"admin"})
    @PatchMapping("/avatar")
    public Result<User> updateUserAvatar(@RequestBody AvatarReq avatarReq, HttpServletRequest request){
//
//        String avatar = avatarReq.getAvatar();
//
//        // 提取数据URL的前缀
//        int commaIndex = avatar.indexOf(',');
//        String dataUrlPrefix = avatar.substring(0, commaIndex);
//        String avatar1 = avatar.substring(commaIndex + 1);
//        // 提取MIME类型并确定文件后缀
//        String mimeType = dataUrlPrefix.split(":")[1].split(";")[0];
//        String fileExtension;
//        switch (mimeType) {
//            case "image/jpeg":
//                fileExtension = ".jpeg";
//                break;
//            case "image/png":
//                fileExtension = ".png";
//                break;
//            // 其他图像类型...
//            default:
//                throw new IllegalArgumentException("Unsupported MIME type: " + mimeType);
//        }
//
//        // 生成一个随机的UUID作为文件名
//        String fileName = UUID.randomUUID().toString() + fileExtension;
//
//        //将base64对象转换成MultipartFile对象
//        Base64ToMultipartFile file = new Base64ToMultipartFile(avatar1,avatar);
//        //获取文件对象的内容类型（例如image/jpeg）
//        String contentType = file.getContentType();
//        //获取文件对象的大小
//        long size = file.getSize();
//
//        // 实例化客户端
//        MinioClient client = MinioClient.builder()
//                .endpoint(this.endpoint)
//                .credentials(this.accessKey, this.secretKey)
//                .build();
//
//
//        // 上传文件到客户端
//        try (InputStream inputStream = file.getInputStream()){
//            client.putObject(PutObjectArgs.builder()
//                    .bucket(this.bucket)		// 指定 Bucket
//                    .contentType(contentType)	// 指定 Content Type
//                    .object(fileName)			// 指定文件的路径
//                    .stream(inputStream, size, -1) // 文件的 Inputstream 流
//                    .build());
//        } catch (IOException | ErrorResponseException | InsufficientDataException | InternalException |
//                 InvalidKeyException | InvalidResponseException | NoSuchAlgorithmException | ServerException |
//                 XmlParserException e) {
//            e.printStackTrace();
//        }
//
//        // 获取当前会话账号id, 并转化为`long`类型
//        long loginIdAsLong = StpUtil.getLoginIdAsLong();
//        User one = userService.getById(loginIdAsLong);
//        one.setUserPic(this.baseUrl + this.bucket +"/"+ fileName);
//        userService.updateById(one);
//        return Result.success(one,"");
        return null;
        // TODO
    }

    /**
     * 更新用户密码
     * @param req
     * @return
     */
    @PatchMapping("/password")
    public Result<String> updateUserPassword(@RequestBody UpdatepwdReq req, HttpServletRequest request){
//        String old_pwd = req.getOld_pwd();
//        String new_pwd = req.getNew_pwd();
//
//        long loginIdAsLong = StpUtil.getLoginIdAsLong();
//        User one = userService.getById(loginIdAsLong);
//
//        //对新密码进行不可逆加密(MD5+随机盐值)
//        new_pwd = PasswordUtil.encrypt(new_pwd);
//
//        boolean b = PasswordUtil.valid(old_pwd, one.getPassword());
//        if ( b ) {
//            one.setPassword(new_pwd);
//            userService.updateById(one);
//            return Result.success("密码修改成功");
//        }
//
//        return Result.error("密码输入错误");
        // TODO
        return null;
    }

    /**
     * 查询所有数据
     *
     * @param
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<List<User>> selectAll() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(User::getCreateTime);
        List<User> list = userService.list(queryWrapper);
        return Result.success(list, "");
    }

    /**
     * 新增用户
     *
     * @param userReq 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public Result<String> insert(@RequestBody InsertUserReq userReq, HttpServletRequest request) {

        User user = new User();
        user.setAccount(userReq.getAccount());
        //对添加的密码进行不可逆加密(MD5+随机盐值)
        String password = PasswordUtil.encrypt(userReq.getPassword());
        user.setPassword(password);
        user.setRoleId(userReq.getRoleId());

        try {
            userService.save(user);

        } catch (Exception e) {
            return Result.error("添加失败");
        }

        return Result.success("添加成功");

    }

    /**
     * 修改用户角色ID
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @PutMapping("/info")
    public Result<String> update(@RequestBody User user) {
        int count = userService.count(new LambdaQueryWrapper<User>()
                .eq(User::getId, user.getId()));
        if (count == 0) {
            return Result.error("用户不存在");
        }
        userService.updateById(user);

        return Result.success("修改成功");
    }

    /**
     * 删除用户
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/del")
    public Result<String> delete(@RequestParam("id") Long id) {
        userService.removeById(id);
        return Result.success("删除成功");
    }

}

