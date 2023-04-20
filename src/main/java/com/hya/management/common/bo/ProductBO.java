package com.hya.management.common.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductBO {
    private Long id;
    private String productName;
    private Long warehouseId;
    private Long number;
    private Float value;
    private String unit;
    private String warehouseName;
    @JsonIgnore
    private Long saleNumber;
}
