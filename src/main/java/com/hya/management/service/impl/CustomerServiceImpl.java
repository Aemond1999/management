package com.hya.management.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.domian.CustomerDO;
import com.hya.management.common.dto.CustomerDTO;
import com.hya.management.common.vo.CustomerVO;
import com.hya.management.constant.Constant;
import com.hya.management.enums.HttpCodeEnum;
import com.hya.management.mapper.CustomerMapper;
import com.hya.management.service.CustomerService;
import com.hya.management.utils.CopyBeanUtil;
import com.hya.management.utils.RedisCache;
import com.hya.management.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, CustomerDO> implements CustomerService {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result customerList() {
        String key = Constant.CACHE_CUSTOMER_PREFIX + "list";
        //获取缓存
        String json = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(json)) {
            List<CustomerVO> customerVOS = JSON.parseArray(json, CustomerVO.class);
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), customerVOS);
        }
        //如果没有缓存则查询数据库
        List<CustomerDO> customerDOS = customerService.list();
        List<CustomerVO> customerVOS = CopyBeanUtil.copyBeanList(customerDOS, CustomerVO.class);
        //如果没有缓存则添加缓存
        redisCache.setCacheObject(key, customerVOS);
        return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), customerVOS);
    }

    @Override
    public Result updateCustomer(CustomerDTO customerDTO) {
        boolean flag = customerService.updateById(CopyBeanUtil.copyBean(customerDTO, CustomerDO.class));
        if (flag) {
            redisCache.deleteObject(Constant.CACHE_CUSTOMER_PREFIX + "list");
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }

    @Override
    public Result deleteCustomer(Long id) {
        boolean flag = customerService.removeById(id);
        if (flag) {
            redisCache.deleteObject(Constant.CACHE_CUSTOMER_PREFIX + "list");
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }


    }
}
