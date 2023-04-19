package com.hya.management.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.domian.ProviderDO;
import com.hya.management.common.domian.WarehouseDO;
import com.hya.management.common.dto.WarehouseDTO;
import com.hya.management.common.vo.ProviderVO;
import com.hya.management.common.vo.WarehouseVO;
import com.hya.management.constant.Constant;
import com.hya.management.enums.HttpCodeEnum;
import com.hya.management.mapper.WarehouseMapper;
import com.hya.management.service.ProviderService;
import com.hya.management.service.WarehouseService;
import com.hya.management.utils.CopyBeanUtil;
import com.hya.management.utils.RedisCache;
import com.hya.management.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, WarehouseDO> implements WarehouseService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private RedisCache redisCache;
    @Override
    public Result warehouseList() {
        String key = Constant.CACHE_WAREHOUSE_PREFIX + "list";
        //获取缓存
        String json = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(json)) {
            List<WarehouseVO> warehouseVOS = JSON.parseArray(json, WarehouseVO.class);
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), warehouseVOS);
        }
        //如果没有缓存则查询数据库
        List<WarehouseDO> warehouseDOS = warehouseService.list();
        List<WarehouseVO> warehouseVOS = CopyBeanUtil.copyBeanList(warehouseDOS, WarehouseVO.class);
        //如果没有缓存则添加缓存
        redisCache.setCacheObject(key,warehouseDOS);
        return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), warehouseVOS);
    }

    @Override
    public Result updateWarehouse(WarehouseDTO warehouseDTO) {
        boolean flag = warehouseService.updateById(CopyBeanUtil.copyBean(warehouseDTO, WarehouseDO.class));
        if (flag) {
            redisCache.deleteObject(Constant.CACHE_WAREHOUSE_PREFIX + "list");
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }

    }

    @Override
    public Result deleteWarehouse(Long id) {
        boolean flag = warehouseService.removeById(id);
        if (flag) {
            redisCache.deleteObject(Constant.CACHE_WAREHOUSE_PREFIX + "list");
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }

    @Override
    public Result addWarehouse(WarehouseDTO warehouseDTO) {
        boolean flag = warehouseService.save(CopyBeanUtil.copyBean(warehouseDTO, WarehouseDO.class));
        if (flag) {
            redisCache.deleteObject(Constant.CACHE_WAREHOUSE_PREFIX+ "list");
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }
}
