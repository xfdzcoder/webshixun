package com.example.webshixun.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor   // 无参构造器
@AllArgsConstructor //全参构造器
public class UpdateUserInfoReq {

    private Long id;

    private String account;

    private String email;

}
