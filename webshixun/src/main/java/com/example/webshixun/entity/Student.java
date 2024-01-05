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
 * (Student)表实体类
 *
 * @author makejava
 * @since 2024-01-04 18:28:34
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@TableName("student")
public class Student implements Serializable {

    private static final long serialVersionUID = -79727185524386880L;

    /**
     * 主键，自增
     */
    
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;


    /**
     * 学生学号，唯一
     */
    
    @TableField("no")
    private String no;


    /**
     * 学生姓名
     */
    
    @TableField("name")
    private String name;


    /**
     * 班级编号
     */
    
    @TableField("class_no")
    private String classNo;


    /**
     * 性别，0：女，1：男
     */
    
    @TableField("sex")
    private String sex;


    /**
     * 学历
     */
    
    @TableField("education")
    private String education;


    /**
     * 邮箱
     */
    
    @TableField("email")
    private String email;


    /**
     * 联系方式
     */
    
    @TableField("phone")
    private String phone;


    /**
     * 家庭住址
     */
    
    @TableField("address")
    private String address;


    /**
     * 创建时间
     */
    
    @TableField("create_time")
    private LocalDateTime createTime;


}

