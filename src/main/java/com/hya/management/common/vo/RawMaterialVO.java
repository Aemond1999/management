package com.hya.management.common.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RawMaterialVO {
    private Long id;
    private String rawMaterialName;
    private Long warehouseId;
    private Long number;
    private Long warning;
    private Float value;
    private String unit;
    private String warehouseName;
}
