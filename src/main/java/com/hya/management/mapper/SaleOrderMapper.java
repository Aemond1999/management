package com.hya.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.management.common.domian.SaleOrderDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface SaleOrderMapper extends BaseMapper<SaleOrderDO> {
    Boolean audit(Long id);
    Boolean updateSaleTimeAndStatus(LocalDateTime time,Long id);
}
