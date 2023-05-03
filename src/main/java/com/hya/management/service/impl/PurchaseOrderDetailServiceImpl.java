package com.hya.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.domain.PurchaseOrderDetailDO;
import com.hya.management.mapper.PurchaseOrderDetailMapper;
import com.hya.management.service.PurchaseOrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderDetailServiceImpl extends ServiceImpl<PurchaseOrderDetailMapper, PurchaseOrderDetailDO> implements PurchaseOrderDetailService {
}
