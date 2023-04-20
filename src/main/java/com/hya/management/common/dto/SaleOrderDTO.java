package com.hya.management.common.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hya.management.common.bo.ProductBO;
import com.hya.management.common.domian.ProviderDO;
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
    private List<ProductBO> detail;
}
