package com.hya.management.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WarehouseVO {
    private Long id;

    private String warehouseName;

    private String address;

    private String description;
}
