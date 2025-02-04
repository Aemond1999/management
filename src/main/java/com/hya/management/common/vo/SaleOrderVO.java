package com.hya.management.common.vo;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class SaleOrderVO {
    private Long id;
    private Long customerId;
    private Long warehouseId;
    private Float sum;
    private String message;
    private Boolean audit;
    private Boolean status;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime saleTime;
    private String customerName;
    private String warehouseName;
    private String empName;
    private List<SaleOrderDetailVO> detail;

}
