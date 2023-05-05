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
@TableName("product")
public class ProductDO {
    @TableId
    private Long id;
    @TableField("product_name")
    private String productName;
    @TableField("warehouse_id")
    private Long warehouseId;
    @TableField("number")
    private Long number;
    @TableField("value")
    private Float value;
    @TableField("unit")
    private String unit;
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
    @TableField(exist = false)
    private Long saleNumber;
}
