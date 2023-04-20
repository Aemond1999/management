package com.hya.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.domian.SaleOrderDO;
import com.hya.management.common.dto.SaleOrderDTO;
import com.hya.management.utils.Result;

public interface SaleOrderService extends IService<SaleOrderDO> {
    Result addSaleOrder(SaleOrderDTO saleOrderDTO);
}
