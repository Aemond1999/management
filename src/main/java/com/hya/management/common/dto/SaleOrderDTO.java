package com.hya.management.common.dto;


import com.hya.management.common.vo.ProductVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
public class SaleOrderDTO {
    private Long id;
    private Long customerId;
    private Long warehouseId;
    private Float sum;
    private String message;
    private List<ProductVO> detail;
}
