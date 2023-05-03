package com.hya.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.domain.*;
import com.hya.management.common.dto.PurchaseOrderDTO;
import com.hya.management.common.dto.PurchaseOrderQueryDTO;
import com.hya.management.common.vo.*;
import com.hya.management.enums.HttpCodeEnum;
import com.hya.management.mapper.PurchaseOrderMapper;
import com.hya.management.service.*;
import com.hya.management.utils.CopyBeanUtil;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrderDO> implements PurchaseOrderService {
    @Autowired
    private PurchaseOrderService purchaseOrderService;
    @Autowired
    private PurchaseOrderDetailService purchaseOrderDetailService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private RawMaterialService rawMaterialService;

    @Override
    public Result addPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrderDO purchaseOrderDO = CopyBeanUtil.copyBean(purchaseOrderDTO, PurchaseOrderDO.class);
        boolean a = purchaseOrderService.save(purchaseOrderDO);
        List<PurchaseOrderDetailDO> purchaseOrderDetailDOS = CopyBeanUtil.copyBeanList(purchaseOrderDTO.getDetail(), PurchaseOrderDetailDO.class);
        purchaseOrderDetailDOS.forEach(s -> {
            s.setCatalogueId(s.getId());
            s.setId(null);
            s.setPurchaseOrderId(purchaseOrderDO.getId());
        });
        boolean b = purchaseOrderDetailService.saveBatch(purchaseOrderDetailDOS);
        if (a && b) {
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }

    @Override
    public Result purchaseOrderList(Long current, Long size, PurchaseOrderQueryDTO query) {
        IPage<PurchaseOrderDO> iPage = new Page<>(current, size);
        //设置查询条件
        LambdaQueryWrapper<PurchaseOrderDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(query.getId() != null, PurchaseOrderDO::getId, query.getId())
                .eq(query.getWarehouseId() != null, PurchaseOrderDO::getWarehouseId, query.getWarehouseId())
                .like(query.getProvId() != null, PurchaseOrderDO::getProvId, query.getProvId());
        List<PurchaseOrderVO> purchaseOrderVOS = CopyBeanUtil.copyBeanList(purchaseOrderService.page(iPage, lqw).getRecords(), PurchaseOrderVO.class);
        purchaseOrderVOS.forEach(s -> {
            s.setProvName(providerService.getById(s.getProvId()).getProvName());
            s.setWarehouseName(warehouseService.getById(s.getWarehouseId()).getWarehouseName());
            s.setEmpName(employeeService.selectEmpName(userService.getById(s.getCreateBy()).getEmpId()));
            List<PurchaseOrderDetailDO> purchaseOrderDetailDOS = purchaseOrderDetailService.list(new LambdaQueryWrapper<PurchaseOrderDetailDO>().eq(PurchaseOrderDetailDO::getPurchaseOrderId, s.getId()));
            List<PurchaseOrderDetailVO> purchaseOrderDetailVOS = CopyBeanUtil.copyBeanList(purchaseOrderDetailDOS, PurchaseOrderDetailVO.class);
            s.setDetail(purchaseOrderDetailVOS);
        });
        PageVO pageVO = new PageVO(purchaseOrderVOS, iPage.getTotal(), current, size);
        return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), pageVO);
    }


    @Override
    public Result audit(Long id) {
        Boolean flag = purchaseOrderMapper.audit(id);
        if (flag) {
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }

    @Override
    public Result purchase(Long id) {
        PurchaseOrderDO purchaseOrder = purchaseOrderService.getById(id);
        if (purchaseOrder.getAudit()) {
            LambdaQueryWrapper<PurchaseOrderDetailDO> one = new LambdaQueryWrapper<>();
            one.eq(PurchaseOrderDetailDO::getPurchaseOrderId, id);
            List<PurchaseOrderDetailDO> purchaseOrderDetailDOS = purchaseOrderDetailService.list(one);
            for (PurchaseOrderDetailDO detail : purchaseOrderDetailDOS) {
                LambdaQueryWrapper<RawMaterialDO> two = new LambdaQueryWrapper<>();
                two.eq(RawMaterialDO::getCatalogueId, detail.getCatalogueId())
                        .eq(RawMaterialDO::getWarehouseId, purchaseOrder.getWarehouseId());
                RawMaterialDO rawMaterial = rawMaterialService.getOne(two);
                if (rawMaterial == null) {
                    RawMaterialDO rawMaterialDO = new RawMaterialDO();
                    rawMaterialDO.setWarehouseId(purchaseOrder.getWarehouseId());
                    rawMaterialDO.setRawMaterialName(detail.getCatalogueName());
                    rawMaterialDO.setNumber(detail.getPurchaseNumber());
                    rawMaterialDO.setUnit(detail.getUnit());
                    rawMaterialDO.setValue(detail.getValue());
                    rawMaterialDO.setCatalogueId(detail.getCatalogueId());
                    rawMaterialService.save(rawMaterialDO);
                } else {
                    rawMaterial.setNumber(rawMaterial.getNumber() + detail.getPurchaseNumber());
                    rawMaterialService.updateById(rawMaterial);
                }
            }
            purchaseOrderMapper.updateSaleTimeAndStatus(LocalDateTime.now(),id);
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
    }
}
