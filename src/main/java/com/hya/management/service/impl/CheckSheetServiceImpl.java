package com.hya.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.domain.CheckSheetDO;
import com.hya.management.common.domain.ProductDO;
import com.hya.management.common.domain.RawMaterialDO;
import com.hya.management.common.dto.CheckSheetDTO;
import com.hya.management.common.dto.CheckSheetQueryDTO;
import com.hya.management.common.vo.CheckSheetVO;
import com.hya.management.common.vo.PageVO;
import com.hya.management.enums.HttpCodeEnum;
import com.hya.management.mapper.CheckSheetMapper;
import com.hya.management.service.*;
import com.hya.management.utils.CopyBeanUtil;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckSheetServiceImpl extends ServiceImpl<CheckSheetMapper, CheckSheetDO> implements CheckSheetService {
    @Autowired
    private CheckSheetService checkSheetService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
    @Autowired
    private RawMaterialService rawMaterialService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CheckSheetMapper checkSheetMapper;

    @Override
    public Result addCheckSheet(CheckSheetDTO checkSheetDTO) {
        CheckSheetDO checkSheetDO = CopyBeanUtil.copyBean(checkSheetDTO, CheckSheetDO.class);
        boolean flag = checkSheetService.save(checkSheetDO);
        if (flag) {
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }

    @Override
    public Result checkSheetList(Long current, Long size, CheckSheetQueryDTO query) {
        IPage<CheckSheetDO> iPage = new Page<>(current, size);
        //设置查询条件
        LambdaQueryWrapper<CheckSheetDO> lqw = new LambdaQueryWrapper<>();
        lqw
                .eq(query.getId() != null, CheckSheetDO::getId, query.getId())
                .eq(query.getType() != null, CheckSheetDO::getType, query.getWarehouseId())
                .eq(query.getWarehouseId() != null, CheckSheetDO::getWarehouseId, query.getWarehouseId());


        List<CheckSheetVO> checkSheetVOS = CopyBeanUtil.copyBeanList(checkSheetService.page(iPage, lqw).getRecords(), CheckSheetVO.class);
        checkSheetVOS.forEach(s -> {
            s.setWarehouseName(warehouseService.getById(s.getWarehouseId()).getWarehouseName());
            s.setEmpName(employeeService.selectEmpName(userService.getById(s.getCreateBy()).getEmpId()));
            if (s.getType() == 1) {
                ProductDO productDO = productService.getById(s.getInventoryId());
                s.setInventory(productDO);
            } else {
                RawMaterialDO rawMaterialDO = rawMaterialService.getById(s.getInventoryId());
                s.setInventory(rawMaterialDO);
            }
        });
        PageVO pageVO = new PageVO(checkSheetVOS, iPage.getTotal(), current, size);
        return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), pageVO);
    }

    @Override
    public Result audit(Long id) {
        Boolean flag = checkSheetMapper.audit(id);
        if (flag) {
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }

    @Override
    public Result adjust(Long id) {
        CheckSheetDO checkSheetDO = checkSheetService.getById(id);
        if (checkSheetDO.getAudit()) {
            if (checkSheetDO.getType() == 1) {
                ProductDO productDO = productService.getById(checkSheetDO.getInventoryId());
                productDO.setNumber(checkSheetDO.getRealNumber());
              productService.updateById(productDO);
            } else {
                RawMaterialDO rawMaterialDO = rawMaterialService.getById(checkSheetDO.getInventoryId());
                rawMaterialDO.setNumber(checkSheetDO.getRealNumber());
               rawMaterialService.updateById(rawMaterialDO);
            }
            checkSheetMapper.updateCheckTimeAndStatus(LocalDateTime.now(), id);
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        }else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }
}
