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
 * (Config)表实体类
 *
 * @author makejava
 * @since 2024-01-04 18:28:33
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@TableName("config")
public class Config implements Serializable {

    private static final long serialVersionUID = -77809042466987401L;

    /**
     * 主键，自增
     */
    
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;


    /**
     * 配置分类名
     */
    
    @TableField("name")
    private String name;


    /**
     * 假期，报销
     */
    
    @TableField("type")
    private String type;


    /**
     * 备注信息
     */
    
    @TableField("remark")
    private String remark;


}

