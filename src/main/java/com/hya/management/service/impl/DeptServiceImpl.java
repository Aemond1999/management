package com.hya.management.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.domain.DeptDO;
import com.hya.management.common.vo.DeptVO;
import com.hya.management.constant.Constant;
import com.hya.management.enums.HttpCodeEnum;
import com.hya.management.mapper.DeptMapper;
import com.hya.management.service.DeptService;
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
public class DeptServiceImpl extends ServiceImpl<DeptMapper, DeptDO> implements DeptService {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private DeptService deptService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result deptList() {
        String key = Constant.CACHE_DEPT_PREFIX + "list";
        //获取缓存
        String json = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(json)) {
            List<DeptVO> deptVOS = JSON.parseArray(json, DeptVO.class);
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), deptVOS);
        }
        //如果没有缓存则查询数据库
        List<DeptDO> deptDOS = deptService.list();
        List<DeptVO> deptVOS = CopyBeanUtil.copyBeanList(deptDOS, DeptVO.class);
        //如果没有缓存则添加缓存
        redisCache.setCacheObject(key, deptVOS,Constant.CACHE_EXPIRE_TTL, TimeUnit.MINUTES);
        return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), deptVOS);
    }

    @Override
    public DeptVO getDeptById(Long id) {
        return CopyBeanUtil.copyBean(deptService.getById(id), DeptVO.class);
    }
}
