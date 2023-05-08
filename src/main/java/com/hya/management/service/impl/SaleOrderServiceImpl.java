package com.hya.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.domain.ProductDO;
import com.hya.management.common.domain.SaleOrderDO;
import com.hya.management.common.domain.SaleOrderDetailDO;
import com.hya.management.common.dto.SaleOrderDTO;
import com.hya.management.common.dto.SaleOrderQueryDTO;
import com.hya.management.common.vo.PageVO;
import com.hya.management.common.vo.SaleOrderDetailVO;
import com.hya.management.common.vo.SaleOrderVO;
import com.hya.management.enums.HttpCodeEnum;
import com.hya.management.mapper.SaleOrderMapper;
import com.hya.management.service.*;
import com.hya.management.utils.CopyBeanUtil;
import com.hya.management.utils.Result;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, SaleOrderDO> implements SaleOrderService {
    @Autowired
    private SaleOrderService saleOrderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SaleOrderDetailService saleOrderDetailService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
    @Autowired
    private SaleOrderMapper saleOrderMapper;

    @Transactional
    @Override
    public Result addSaleOrder(SaleOrderDTO saleOrderDTO) {
        SaleOrderDO saleOrderDO = CopyBeanUtil.copyBean(saleOrderDTO, SaleOrderDO.class);
        boolean a = saleOrderService.save(saleOrderDO);
        List<SaleOrderDetailDO> saleOrderDetailDOS = CopyBeanUtil.copyBeanList(saleOrderDTO.getDetail(), SaleOrderDetailDO.class);
        saleOrderDetailDOS.forEach(s -> {
            s.setProductId(s.getId());
            s.setId(null);
            s.setSaleOrderId(saleOrderDO.getId());
        });
        boolean b = saleOrderDetailService.saveBatch(saleOrderDetailDOS);
        if (a && b) {
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }

    @Override
    public Result saleOrderList(Long current, Long size, @NotNull SaleOrderQueryDTO query) {
        IPage<SaleOrderDO> iPage = new Page<>(current, size);
        //设置查询条件
        LambdaQueryWrapper<SaleOrderDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(query.getId() != null, SaleOrderDO::getId, query.getId())
                .eq(query.getWarehouseId() != null, SaleOrderDO::getWarehouseId, query.getWarehouseId())
                .like(query.getCustomerId() != null, SaleOrderDO::getCustomerId, query.getCustomerId());
        List<SaleOrderVO> saleOrderVOS = CopyBeanUtil.copyBeanList(saleOrderService.page(iPage, lqw).getRecords(), SaleOrderVO.class);
        saleOrderVOS.forEach(s -> {
            s.setCustomerName(customerService.getById(s.getCustomerId()).getCustomerName());
            s.setWarehouseName(warehouseService.getById(s.getWarehouseId()).getWarehouseName());
            s.setEmpName(employeeService.selectEmpName(userService.getById(s.getCreateBy()).getEmpId()));
            LambdaQueryWrapper<SaleOrderDetailDO> sqw = new LambdaQueryWrapper<>();
            sqw.eq(SaleOrderDetailDO::getSaleOrderId, s.getId());
            List<SaleOrderDetailDO> saleOrderDetailDOS = saleOrderDetailService.list(sqw);
            List<SaleOrderDetailVO> saleOrderDetailVOS = CopyBeanUtil.copyBeanList(saleOrderDetailDOS, SaleOrderDetailVO.class);
            s.setDetail(saleOrderDetailVOS);
        });
        PageVO pageVO = new PageVO(saleOrderVOS, iPage.getTotal(), current, size);
        return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), pageVO);
    }


    @Override
    public Result audit(Long id) {
        Boolean flag = saleOrderMapper.audit(id);
        if (flag) {
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }

    @Transactional
    @Override
    public Result sale(Long id) {
        if (saleOrderService.getById(id).getAudit()) {
            List<SaleOrderDetailDO> saleOrderDetailDOS = saleOrderDetailService.list(new LambdaQueryWrapper<SaleOrderDetailDO>().eq(SaleOrderDetailDO::getSaleOrderId, id));
            for (SaleOrderDetailDO s : saleOrderDetailDOS) {
                ProductDO productDO = productService.getById(s.getProductId());
                if (productDO.getNumber() - s.getSaleNumber() > 0) {
                    productDO.setNumber(productDO.getNumber() - s.getSaleNumber());
                    productService.updateById(productDO);
                } else {
                    return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
                }
            }
            saleOrderMapper.updateSaleTimeAndStatus(LocalDateTime.now(), id);
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
    }
}
