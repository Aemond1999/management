package com.hya.management.common.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@TableName("purchase_order")
public class PurchaseOrderDO {
    @TableId
    private Long id;
    @TableField("prov_id")
    private Long provId;
    @TableField("warehouse_id")
    private Long warehouseId;
    @TableField("sum")
    private Float sum;
    @TableField("message")
    private String message;
    @TableField("is_audit")
    private Boolean audit;
    @TableField("status")
    private Boolean status;
    @TableField("deleted")
    private Boolean deleted;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @TableField("purchase_time")
    private LocalDateTime purchaseTime;
}
