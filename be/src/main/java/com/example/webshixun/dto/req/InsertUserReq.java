package com.example.webshixun.dto.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;


@Data
public class InsertUserReq {

    //主键  在序列化时自动将该字段类型转为String类型返给前端
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    //登录账号
    private String account;
    //登录密码
    private String password;
    //角色
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer roleId;

}
