package com.hya.management.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
public class PurchaseOrderVO {
    private Long id;
    private Long provId;
    private Long warehouseId;
    private Float sum;
    private String message;
    private Boolean audit;
    private Boolean status;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime purchaseTime;
    private String provName;
    private String warehouseName;
    private String empName;
    private List<PurchaseOrderDetailVO> detail;
}
