package com.hya.management.common.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVO {
    private Long id;
    private String productName;
    private Long warehouseId;
    private Long number;
    private Float value;
    private String unit;
    private String warehouseName;
}
