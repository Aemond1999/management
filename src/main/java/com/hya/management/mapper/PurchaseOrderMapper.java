package com.hya.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.management.common.domain.PurchaseOrderDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface PurchaseOrderMapper extends BaseMapper<PurchaseOrderDO> {
    Boolean audit(Long id);
    Boolean updateSaleTimeAndStatus(LocalDateTime time, Long id);
}
