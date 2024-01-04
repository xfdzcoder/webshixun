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
 * (Employee)表实体类
 *
 * @author makejava
 * @since 2024-01-04 18:28:33
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@TableName("employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 885181418838025388L;

    /**
     * 主键，自增
     */
    
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;


    /**
     * 员工编号，唯一
     */
    
    @TableField("no")
    private String no;


    /**
     * 员工姓名
     */
    
    @TableField("name")
    private String name;


    /**
     * 所属部门，部门表的部门ID
     */
    
    @TableField("dept_id")
    private Integer deptId;


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
     * 入职时间
     */
    
    @TableField("entry_time")
    private String entryTime;


    /**
     * 创建时间
     */
    
    @TableField("create_time")
    private LocalDateTime createTime;


}

