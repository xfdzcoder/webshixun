package com.example.webshixun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2024-01-04 18:28:34
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = -35926715138396938L;

    /**
     * 主键
     */
    
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;


    /**
     * 登录账号
     */
    
    @TableField("account")
    private String account;


    /**
     * 登录密码
     */
    
    @TableField("password")
    private String password;


    /**
     * 盐
     */
    
    @TableField("salt")
    private String salt;


    /**
     * 盐的索引
     */
    
    @TableField("salt_index")
    private Integer saltIndex;


    /**
     * 状态
     */
    
    @TableField("status")
    private Integer status;


    /**
     * 错误次数
     */
    
    @TableField("error")
    private Integer error;


    /**
     * 所属员工ID
     */
    
    @TableField("emp_id")
    private Integer empId;


    /**
     * 邮箱
     */
    
    @TableField("email")
    private String email;


    /**
     * 用户头像图片名
     */
    
    @TableField("user_pic")
    private String userPic;


    /**
     * 角色
     */
    
    @TableField("role_id")
    private Integer roleId;


    /**
     * 创建时间
     */
    
    @TableField("create_time")
    private LocalDateTime createTime;


}

