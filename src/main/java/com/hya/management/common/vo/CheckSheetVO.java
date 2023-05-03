package com.hya.management.common.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class CheckSheetVO {

    private Long id;
    private String message;
    private Boolean audit;
    private Boolean status;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime checkTime;
    private int type;
    private Long realNumber;
    private Long originalNumber;
    private Long inventoryId;
    private String empName;
    private Object inventory;
    private Long warehouseId;
    private String warehouseName;

}
