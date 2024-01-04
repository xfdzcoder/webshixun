package com.example.webshixun.dto.req;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class AddPermissionReq {

    //主键  在序列化时自动将该字段类型转为String类型返回给前端
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    //角色ID
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;
    //角色名称

    private String roleName;

    //菜单ID
    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;

    private String menuName;


    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
