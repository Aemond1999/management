package com.hya.management.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseOrderDetailVO {
    private Long catalogueId;
    private String catalogueName;
    private Long purchaseNumber;
    private String unit;
    private Float value;
}
