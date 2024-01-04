package com.example.webshixun.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor   // 无参构造器
@AllArgsConstructor //全参构造器
public class MenuResp {

    private String dept;

    private String employee;

    private String userManager;

    private String holiday;

    private String reimburse;

    private String email;

    private String role;

    private String permission;

    private String password;

    private String avatar;

    private String clz;

    private String student;
}
