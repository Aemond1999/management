package com.hya.management.common.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class SaleOrderDetailVO {
    private Long productId;
    private String productName;
    private Long saleNumber;
    private String unit;
    private Float value;
}
