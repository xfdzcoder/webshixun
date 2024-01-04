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

/**
 * (Dept)表实体类
 *
 * @author makejava
 * @since 2024-01-04 18:28:33
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@TableName("dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 257789458564841121L;

    /**
     * 主键，自增
     */
    
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;


    /**
     * 部门编号，唯一
     */
    
    @TableField("no")
    private String no;


    /**
     * 部门名称
     */
    
    @TableField("name")
    private String name;


    /**
     * 所属上一级部门编号，最高总公司，二级个部门
     */
    
    @TableField("pid")
    private String pid;


    /**
     * 负责人
     */
    
    @TableField("user")
    private String user;


    /**
     * 地址
     */
    
    @TableField("address")
    private String address;


}

