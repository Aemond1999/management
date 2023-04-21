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
@TableName("sale_order_detail")
public class SaleOrderDetailDO {
    @TableId
    private Long id;
    @TableField("sale_order_id")
    private Long saleOrderId;
    @TableField("product_id")
    private Long productId;
    @TableField("product_name")
    private String productName;
    @TableField("sale_number")
    private Long saleNumber;
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
