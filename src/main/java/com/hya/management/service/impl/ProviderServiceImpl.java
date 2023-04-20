package com.hya.management.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.domian.CustomerDO;
import com.hya.management.common.domian.ProviderDO;
import com.hya.management.common.dto.ProviderDTO;
import com.hya.management.common.vo.CustomerVO;
import com.hya.management.common.vo.ProviderVO;
import com.hya.management.constant.Constant;
import com.hya.management.enums.HttpCodeEnum;
import com.hya.management.mapper.ProviderMapper;
import com.hya.management.service.ProviderService;
import com.hya.management.utils.CopyBeanUtil;
import com.hya.management.utils.RedisCache;
import com.hya.management.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, ProviderDO> implements ProviderService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private  ProviderService providerService;
    @Autowired
    private RedisCache redisCache;
    @Override
    public Result providerList() {
        String key = Constant.CACHE_PROVIDER_PREFIX + "list";
        //获取缓存
        String json = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(json)) {
            List<ProviderVO> providerVOS = JSON.parseArray(json, ProviderVO.class);
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), providerVOS);
        }
        //如果没有缓存则查询数据库
        List<ProviderDO> providerDOS = providerService.list();
        List<ProviderVO> providerVOS = CopyBeanUtil.copyBeanList(providerDOS, ProviderVO.class);
        //如果没有缓存则添加缓存
        redisCache.setCacheObject(key,providerVOS);
        return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), providerVOS);
    }

    @Override
    public Result updateProvider(ProviderDTO providerDTO) {
        boolean flag = providerService.updateById(CopyBeanUtil.copyBean(providerDTO, ProviderDO.class));
        if (flag) {
            redisCache.deleteObject(Constant.CACHE_PROVIDER_PREFIX + "list");
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }

    }

    @Override
    public Result deleteProvider(Long id) {
        boolean flag = providerService.removeById(id);
        if (flag) {
            redisCache.deleteObject(Constant.CACHE_PROVIDER_PREFIX + "list");
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }

    @Override
    public Result addProvider(ProviderDTO providerDTO) {
        boolean flag = providerService.save(CopyBeanUtil.copyBean(providerDTO, ProviderDO.class));
        if (flag) {
            redisCache.deleteObject(Constant.CACHE_PROVIDER_PREFIX+ "list");
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }
}
