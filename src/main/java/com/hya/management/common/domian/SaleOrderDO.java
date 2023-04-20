package com.hya.management.common.domian;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hya.management.common.bo.ProductBO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@TableName("sale_order")
public class SaleOrderDO {
    @TableId
    private Long id;
    @TableField("customer_id")
    private Long customerId;
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
    @TableField(value = "use_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime useTime;
    @TableField(value = "use_by", fill = FieldFill.INSERT_UPDATE)
    private Long useBy;
    @TableField(exist = false)
    private List<ProductBO> detail;


}
