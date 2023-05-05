package com.hya.management.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.domain.RoleDO;
import com.hya.management.common.vo.RoleVO;
import com.hya.management.constant.Constant;
import com.hya.management.enums.HttpCodeEnum;
import com.hya.management.mapper.RoleMapper;
import com.hya.management.service.RoleService;
import com.hya.management.utils.CopyBeanUtil;
import com.hya.management.utils.RedisCache;
import com.hya.management.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result roleList() {
        String key = Constant.CACHE_ROLE_PREFIX + "list";
        //获取缓存
        String json = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(json)) {
            List<RoleVO> roleVOS = JSON.parseArray(json, RoleVO.class);
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), roleVOS);
        }
        //如果没有缓存则查询数据库
        List<RoleDO> roleDOS = roleService.list();
        List<RoleVO> roleVOS = CopyBeanUtil.copyBeanList(roleDOS, RoleVO.class);
        //如果没有缓存则添加缓存
        redisCache.setCacheObject(key, roleVOS,Constant.CACHE_EXPIRE_TTL, TimeUnit.MINUTES);
        return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), roleVOS);
    }
}
