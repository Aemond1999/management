package com.hya.management.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleOrderQueryDTO {
    private Long id;

    private Long customerId;

    private Long warehouseId;
}
