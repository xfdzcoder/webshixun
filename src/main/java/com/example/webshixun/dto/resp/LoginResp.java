package com.example.webshixun.dto.resp;


import com.example.webshixun.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor   // 无参构造器
@AllArgsConstructor //全参构造器
public class LoginResp {

    private User user;

    private  String token;

    private MenuResp menuResp;

    private String role;
}
