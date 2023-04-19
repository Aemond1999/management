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
    @TableField("create_by")
    private Long createBy;
    @TableField(value = "update_time",fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField("update_by")
    private String updateBy;
}
