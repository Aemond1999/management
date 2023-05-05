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
@TableName("purchase_order_detail")
public class PurchaseOrderDetailDO {
    @TableId
    private Long id;
    @TableField("purchase_order_id")
    private Long purchaseOrderId;
    @TableField("catalogue_id")
       private Long catalogueId;
    @TableField("catalogue_name")
    private String catalogueName;
    @TableField("purchase_number")
    private Long purchaseNumber;
    @TableField("unit")
    private String unit;
    @TableField("deleted")
    private Boolean deleted;
    @TableField("value")
    private Float value;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
}
