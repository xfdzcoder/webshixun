package com.example.webshixun.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 * (Holiday)表实体类
 *
 * @author makejava
 * @since 2024-01-04 18:28:33
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@TableName("holiday")
public class Holiday implements Serializable {

    private static final long serialVersionUID = -25052010255520248L;

    /**
     * 主键，自增
     */
    
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;


    /**
     * 请假编号，唯一，系统生成，birth日期时间，年月日时分秒+四位随机数
     */
    
    @TableField("no")
    private String no;


    /**
     * 账户编号
     */
    
    @TableField("user_no")
    private String userNo;


    /**
     * 请假类型，这些数据都在配置表中，1：事假，2：婚假，3：年假，4：调休，5：病假，6：丧假
     */
    
    @TableField("type_id")
    private Integer typeId;


    /**
     * 请假事由
     */
    
    @TableField("bz")
    private String bz;


    /**
     * 开始时间
     */
    
    @TableField("start_time")
    private LocalDateTime startTime;


    /**
     * 结束时间
     */
    
    @TableField("end_time")
    private LocalDateTime endTime;


    /**
     * 申请状态，草稿，已提交，
     */
    
    @TableField("status")
    private String status;


    /**
     * 审批理由，同意：同意，驳回：驳回的理由
     */
    
    @TableField("no_agree")
    private String noAgree;


    /**
     * 创建时间
     */
    
    @TableField("create_time")
    private LocalDateTime createTime;


    /**
     * 修改时间，提交时间或审批时间
     */
    
    @TableField("update_time")
    private LocalDateTime updateTime;


}

