package com.example.webshixun.vo.req;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: ftt
 * @createDate: 2023/12/28
 */
@Data
public class AddHolidayReq {


    //请假编号，唯一，系统生成，birth日期时间，年月日时分秒+四位随机数
    private String no;
    //账户编号
    private String userNo;
    //请假类型，这些数据都在配置表中，1：事假，2：婚假，3：年假，4：调休，5：病假，6：丧假
    private String typeId;
    //请假事由
    private String bz;
    //开始时间
    private LocalDateTime startTime;
    //结束时间
    private LocalDateTime endTime;
    //申请状态，1：草稿，2：提交，
    private String status;
    //审批状态
    private String noAgree;

}
