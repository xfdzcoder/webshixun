package com.example.webshixun.dto.req;

import lombok.Data;


@Data
public class UpdatepwdReq {

    private String old_pwd;

    private String new_pwd;

    private String re_pwd;
}
