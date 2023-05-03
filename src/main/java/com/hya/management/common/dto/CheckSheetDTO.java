package com.hya.management.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CheckSheetDTO {
    private Long id;
    private String message;
    private Integer  type;
    private Long realNumber;
    private Long originalNumber;
    private Long warehouseId;
    private Long inventoryId;
}
