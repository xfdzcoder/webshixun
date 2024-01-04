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
 * (Class)表实体类
 *
 * @author makejava
 * @since 2024-01-04 18:28:32
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@TableName("class")
public class Clz implements Serializable {

    private static final long serialVersionUID = 946607307324380638L;

    /**
     * 主键，自增
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;


    /**
     * 班级编号，唯一
     */

    @TableField("no")
    private String no;


    /**
     * 班级名称
     */
    
    @TableField("name")
    private String name;


    /**
     * 班主任
     */
    
    @TableField("user")
    private String user;


    /**
     * 班级地址
     */
    
    @TableField("address")
    private String address;


}

