package com.hya.management.common.dto;

import com.hya.management.common.vo.CatalogueVO;
import com.hya.management.common.vo.ProductVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class PurchaseOrderDTO {
    private Long id;
    private Long provId;
    private Long warehouseId;
    private Float sum;
    private String message;
    private List<CatalogueVO> detail;
}
