package com.hya.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.bo.ProductBO;
import com.hya.management.common.domian.ProductDO;
import com.hya.management.common.dto.ProductDTO;
import com.hya.management.common.dto.ProductQueryDTO;
import com.hya.management.common.vo.PageVO;
import com.hya.management.common.vo.ProductVO;
import com.hya.management.constant.Constant;
import com.hya.management.enums.HttpCodeEnum;
import com.hya.management.mapper.ProductMapper;
import com.hya.management.service.ProductService;
import com.hya.management.service.WarehouseService;
import com.hya.management.utils.CopyBeanUtil;
import com.hya.management.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductDO> implements ProductService {
    @Autowired
    private ProductService productService;
    @Autowired
    private WarehouseService warehouseService;

    @Override
    public Result productList(Long current, Long size, @NotNull ProductQueryDTO productQueryDTO) {
        IPage<ProductDO> iPage = new Page<>(current, size);
        //设置查询条件
        LambdaQueryWrapper<ProductDO> lqw = new LambdaQueryWrapper<>();
        lqw
                .eq(productQueryDTO.getId() != null, ProductDO::getId, productQueryDTO.getId())
                .like(StringUtils.isNotBlank(productQueryDTO.getProductName()), ProductDO::getProductName, productQueryDTO.getProductName())
                .like(productQueryDTO.getWarehouseId() != null, ProductDO::getWarehouseId, productQueryDTO.getWarehouseId());
        //获取对应数据
        List<ProductVO> products = CopyBeanUtil.copyBeanList(productService.page(iPage, lqw).getRecords(), ProductVO.class);
        products.forEach(s->{
                 String warehouseName = warehouseService.getById(s.getWarehouseId()).getWarehouseName();
                 s.setWarehouseName(warehouseName);
        });
        PageVO pageVO = new PageVO(products, iPage.getTotal(), current, size);
        return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), pageVO);
    }



    @Override
    public Result updateProduct(ProductDTO productDTO) {
        boolean flag = productService.updateById(CopyBeanUtil.copyBean(productDTO, ProductDO.class));
        if (flag) {
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }

    @Override
    public Result deleteProduct(Long id) {
        boolean flag = productService.removeById(id);
        if (flag) {
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }

    @Override
    public List<ProductBO> productListByWarehouseId(Long id) {
        LambdaQueryWrapper<ProductDO> lqw =new LambdaQueryWrapper<>();
        lqw.eq(ProductDO::getWarehouseId,id);
        return  CopyBeanUtil.copyBeanList(productService.list(lqw), ProductBO.class);
    }
}
