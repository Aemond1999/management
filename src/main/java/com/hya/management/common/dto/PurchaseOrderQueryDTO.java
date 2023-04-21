package com.hya.management.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseOrderQueryDTO {
    private Long id;

    private Long provId;

    private Long warehouseId;
}
