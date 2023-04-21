package com.hya.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.domian.PurchaseOrderDO;
import com.hya.management.common.dto.PurchaseOrderDTO;
import com.hya.management.common.dto.PurchaseOrderQueryDTO;
import com.hya.management.common.dto.SaleOrderQueryDTO;
import com.hya.management.utils.Result;

public interface PurchaseOrderService extends IService<PurchaseOrderDO> {
    Result addPurchaseOrder( PurchaseOrderDTO purchaseOrderDTO);

    Result purchaseOrderList(Long current, Long size, PurchaseOrderQueryDTO query);

    Result audit(Long id);

    Result purchase(Long id);
}
