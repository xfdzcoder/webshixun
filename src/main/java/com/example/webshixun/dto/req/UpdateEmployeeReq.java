package com.example.webshixun.dto.req;

import lombok.Data;


@Data
public class UpdateEmployeeReq {

    //主键
    private Long id;

    //员工编号，唯一
    private String no;

    //员工姓名
    private String name;

    //所属部门，部门表的部门ID
    private String deptId;

    //性别，0：女，1：男
    private String sex;

    //入职时间
    private String entryTime;
}
