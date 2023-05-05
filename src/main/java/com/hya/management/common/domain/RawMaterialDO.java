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
@TableName("raw_material")
public class RawMaterialDO {
    @TableId
    private Long id;
    @TableField("raw_material_name")
    private String rawMaterialName;
    @TableField("warehouse_id")
    private Long warehouseId;
    @TableField("catalogue_id")
    private Long catalogueId;
    @TableField("number")
    private Long number;
    @TableField("warning")
    private Long warning;
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
}
