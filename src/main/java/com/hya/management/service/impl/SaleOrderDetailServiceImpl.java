package com.hya.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.domain.SaleOrderDetailDO;
import com.hya.management.mapper.SaleOrderDetailMapper;
import com.hya.management.service.SaleOrderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SaleOrderDetailServiceImpl extends ServiceImpl<SaleOrderDetailMapper, SaleOrderDetailDO> implements SaleOrderDetailService {
}
