package com.hya.management.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckSheetQueryDTO {
    private Long id;
    private Integer  type;
    private Long warehouseId;
}

