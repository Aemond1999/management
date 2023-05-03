package com.hya.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.domain.CatalogueDO;
import com.hya.management.common.vo.CatalogueVO;
import com.hya.management.enums.HttpCodeEnum;
import com.hya.management.mapper.CatalogueMapper;
import com.hya.management.service.CatalogueService;
import com.hya.management.utils.CopyBeanUtil;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogueServiceImpl extends ServiceImpl<CatalogueMapper, CatalogueDO> implements CatalogueService {
    @Autowired
    private CatalogueService catalogueService;

    public Result catalogueListByProvId(Long id){
        LambdaQueryWrapper<CatalogueDO> lqw=new LambdaQueryWrapper<>();
        lqw.eq(CatalogueDO::getProvId,id);
        List<CatalogueVO> catalogueVOS = CopyBeanUtil.copyBeanList(catalogueService.list(lqw), CatalogueVO.class);
        return Result.okResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.SUCCESS.getMsg(),catalogueVOS);
    }
}
