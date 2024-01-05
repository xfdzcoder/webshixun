package com.example.webshixun.vo.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.InetAddressSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: ftt
 * @createDate: 2024/1/1
 */
@Data
public class HolidayResp {
    //主键，自增  在序列化时自动将该字段转为String类型返回给前端
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    //请假编号，唯一，系统生成，birth日期时间，年月日时分秒+四位随机数
    private String no;
    //账户编号
    private String userNo;
    //请假类型，这些数据都在配置表中，1：事假，2：婚假，3：年假，4：调休，5：病假，6：丧假
    private Integer typeId;
    //请假事由
    private String bz;
    //开始时间
    private LocalDateTime startTime;
    //结束时间
    private LocalDateTime endTime;
    //申请状态，1：草稿，2：提交，
    private String status;
    //审批理由，同意：同意，驳回：驳回的理由
    private String noAgree;

}
