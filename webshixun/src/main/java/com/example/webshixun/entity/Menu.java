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
 * (Menu)表实体类
 *
 * @author makejava
 * @since 2024-01-04 18:28:33
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@TableName("menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 514584745195500841L;

    /**
     * 主键
     */
    
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;


    /**
     * 菜单名称
     */
    
    @TableField("name")
    private String name;


    /**
     * 提交的url
     */
    
    @TableField("href_url")
    private String hrefUrl;


    /**
     * 父节点，三级菜单，一级:人事管理，二级：请假管理，三级：添加部门
     */
    
    @TableField("parent_id")
    private Integer parentId;


    /**
     * 创建时间
     */
    
    @TableField("create_time")
    private LocalDateTime createTime;


}

