package com.example.webshixun.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 * (Permissions)表实体类
 *
 * @author makejava
 * @since 2024-01-04 18:28:34
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@TableName("permissions")
public class Permissions implements Serializable {

    private static final long serialVersionUID = -35945848804704898L;


    /**
     * 主键
     */
    
    @TableField("id")
    private Long id;


    /**
     * 角色ID
     */
    
    @TableField("role_id")
    private Long roleId;


    /**
     * 角色名称
     */
    
    @TableField("role_name")
    private String roleName;


    /**
     * 菜单ID
     */
    
    @TableField("menu_id")
    private Long menuId;


    /**
     * 菜单名称
     */
    
    @TableField("menu_name")
    private String menuName;


    /**
     * 是否有这个权限，0：没有，1：有
     */
    
    @TableField("use_it")
    private Integer useIt;


    /**
     * 创建时间
     */
    
    @TableField("create_time")
    private LocalDateTime createTime;


}

