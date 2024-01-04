package com.example.webshixun.vo.req;

import lombok.Data;


@Data
public class UpdatepwdReq {

    private String oldPwd;

    private String newPwd;

    private String rePwd;

    private Long id;
}
