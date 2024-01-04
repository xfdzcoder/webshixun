package com.example.webshixun.dto.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;


@Data
public class InsertRoleReq {

    //主键 在序列化时自动将该字段类型转为String类型返回给前端
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;
    //角色名称
    private String name;
}
