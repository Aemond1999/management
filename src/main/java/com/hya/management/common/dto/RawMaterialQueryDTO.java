package com.hya.management.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RawMaterialQueryDTO {
    private Long id;
    private String rawMaterialName;
    private Long warehouseId;
}
