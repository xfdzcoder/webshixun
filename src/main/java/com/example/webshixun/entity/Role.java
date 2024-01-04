package com.example.webshixun.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 * (Role)表实体类
 *
 * @author makejava
 * @since 2024-01-04 18:28:34
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@TableName("role")
public class Role implements Serializable {

    private static final long serialVersionUID = -50714275871779501L;

    /**
     * 主键
     */
    
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;


    /**
     * 角色名称
     */
    
    @TableField("name")
    private String name;


    /**
     * 创建时间
     */
    
    @TableField("create_time")
    private LocalDateTime createTime;


}

