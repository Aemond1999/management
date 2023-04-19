package com.hya.management.common.domian;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@TableName("customer")
public class CustomerDO {
    @TableId
    private Long id;
    @TableField("customer_name")
    private String customerName;
    @TableField("linkman")
    private String linkman;
    @TableField("phone")
    private Long phone;
    @TableField("address")
    private String address;
    @TableField("bank_name")
    private String bankName;
    @TableField("bank_card")
    private Long bankCard;
    @TableField("deleted")
    private Boolean deleted;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField("create_by")
    private Long createBy;
    @TableField(value = "update_time",fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField("update_by")
    private String updateBy;
}
