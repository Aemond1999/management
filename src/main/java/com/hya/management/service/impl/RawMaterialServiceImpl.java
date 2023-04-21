package com.hya.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.domian.ProductDO;
import com.hya.management.common.domian.RawMaterialDO;
import com.hya.management.common.dto.RawMaterialDTO;
import com.hya.management.common.dto.RawMaterialQueryDTO;
import com.hya.management.common.vo.PageVO;
import com.hya.management.common.vo.ProductVO;
import com.hya.management.common.vo.RawMaterialVO;
import com.hya.management.enums.HttpCodeEnum;
import com.hya.management.mapper.RawMaterialMapper;
import com.hya.management.service.ProductService;
import com.hya.management.service.RawMaterialService;
import com.hya.management.service.WarehouseService;
import com.hya.management.utils.CopyBeanUtil;
import com.hya.management.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("all")
public class RawMaterialServiceImpl extends ServiceImpl<RawMaterialMapper, RawMaterialDO> implements RawMaterialService {
    @Autowired
    private RawMaterialService rawMaterialService;
    @Autowired
    private WarehouseService warehouseService;

    @Override
    public Result rawMaterialList(Long current, Long size, RawMaterialQueryDTO rawMaterialQueryDTO) {
        IPage<RawMaterialDO> iPage = new Page<>(current, size);
        //设置查询条件
        LambdaQueryWrapper<RawMaterialDO> lqw = new LambdaQueryWrapper<>();
        lqw
                .eq(rawMaterialQueryDTO.getId() != null, RawMaterialDO::getId, rawMaterialQueryDTO.getId())
                .like(StringUtils.isNotBlank(rawMaterialQueryDTO.getRawMaterialName()), RawMaterialDO::getRawMaterialName, rawMaterialQueryDTO.getRawMaterialName())
                .like(rawMaterialQueryDTO.getWarehouseId() != null, RawMaterialDO::getWarehouseId, rawMaterialQueryDTO.getWarehouseId());
        //获取对应数据
        List<RawMaterialVO> rawMaterialVOS = CopyBeanUtil.copyBeanList(rawMaterialService.page(iPage, lqw).getRecords(), RawMaterialVO.class);
        rawMaterialVOS.forEach(s -> {
            String warehouseName = warehouseService.getById(s.getWarehouseId()).getWarehouseName();
            s.setWarehouseName(warehouseName);
        });
        PageVO pageVO = new PageVO(rawMaterialVOS, iPage.getTotal(), current, size);
        return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), pageVO);
    }

    @Override
    public Result updateRawMaterial(RawMaterialDTO rawMaterialDTO) {
        boolean flag = rawMaterialService.updateById(CopyBeanUtil.copyBean(rawMaterialDTO, RawMaterialDO.class));
        if (flag) {
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }

    @Override
    public Result deleteRawMaterial(Long id) {
        boolean flag = rawMaterialService.removeById(id);
        if (flag) {
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }
}
