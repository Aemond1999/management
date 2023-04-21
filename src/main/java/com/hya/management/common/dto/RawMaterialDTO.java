package com.hya.management.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RawMaterialDTO {
    private Long id;
    private String rawMaterialName;
    private Long warehouseId;
    private Long number;
    private Long warning;
    private Float value;
    private String unit;

}
