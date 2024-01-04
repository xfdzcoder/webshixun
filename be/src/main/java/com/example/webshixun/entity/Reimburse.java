package com.example.webshixun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (Reimburse)表实体类
 *
 * @author makejava
 * @since 2024-01-04 18:28:34
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@TableName("reimburse")
public class Reimburse implements Serializable {

    private static final long serialVersionUID = -12476631580729023L;

    /**
     * 逐渐，自增
     */
    
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;


    /**
     * 报销编号，唯一，系统生成，年月日时分秒+四位随机数
     */
    
    @TableField("no")
    private String no;


    /**
     * 用户账号ID
     */
    
    @TableField("user_no")
    private Integer userNo;


    /**
     * 报销类型，这些数据都在配置表中，1：住宿费，2：交通费，3：差旅费，4：招待费，5：办公费，6：项目费
     */
    
    @TableField("type_id")
    private Integer typeId;


    /**
     * 报销事由
     */
    
    @TableField("bz")
    private String bz;


    /**
     * 申请状态，1：草稿，2：提交
     */
    
    @TableField("status")
    private Integer status;


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

