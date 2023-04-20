package com.hya.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.domian.SaleOrderDO;
import com.hya.management.common.domian.SaleOrderDetailDO;
import com.hya.management.common.dto.SaleOrderDTO;
import com.hya.management.constant.Constant;
import com.hya.management.enums.HttpCodeEnum;
import com.hya.management.mapper.SaleOrderMapper;
import com.hya.management.service.ProductService;
import com.hya.management.service.SaleOrderDetailService;
import com.hya.management.service.SaleOrderService;
import com.hya.management.utils.CopyBeanUtil;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@Service
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, SaleOrderDO> implements SaleOrderService {
    @Autowired
    private SaleOrderService saleOrderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SaleOrderDetailService saleOrderDetailService;

    @Override
    public Result addSaleOrder(SaleOrderDTO saleOrderDTO) {
        SaleOrderDO saleOrderDO = CopyBeanUtil.copyBean(saleOrderDTO, SaleOrderDO.class);
        boolean a= saleOrderService.save(saleOrderDO);
        List<SaleOrderDetailDO> saleOrderDetailDOS = CopyBeanUtil.copyBeanList(saleOrderDTO.getDetail(), SaleOrderDetailDO.class);
        List<SaleOrderDetailDO> collect = saleOrderDetailDOS.stream().map(s -> {
            s.setProductId(s.getId());
            s.setId(null);
            s.setSaleOrderId(saleOrderDO.getId());
            return s;
        }).collect(Collectors.toList());

        boolean b = saleOrderDetailService.saveBatch(collect);
        if (a&&b) {
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }
}
