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
@TableName("warehouse")
public class WarehouseDO {
    @TableId
    private Long id;
    @TableField("warehouse_name")
    private String warehouseName;
    @TableField("address")
    private String address;
    @TableField("description")
    private String description;
    @TableField("deleted")
    private Boolean deleted;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField( value = "create_by",fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(value = "update_time",fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(value = "update_by",fill=FieldFill.INSERT_UPDATE)
    private Long updateBy;
}
