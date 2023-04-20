package com.hya.management.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductQueryDTO {
    private Long id;
    private Long warehouseId;
    private String productName;
}
