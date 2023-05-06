package com.hya.management.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.domain.EmployeeDO;
import com.hya.management.common.dto.EmployeeDTO;
import com.hya.management.common.vo.DeptVO;
import com.hya.management.common.vo.EmployeeVO;
import com.hya.management.constant.Constant;
import com.hya.management.enums.HttpCodeEnum;
import com.hya.management.mapper.EmployeeMapper;
import com.hya.management.service.DeptService;
import com.hya.management.service.EmployeeService;
import com.hya.management.utils.CopyBeanUtil;
import com.hya.management.utils.RedisCache;
import com.hya.management.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, EmployeeDO> implements EmployeeService {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Result employeeList() {
        String key = Constant.CACHE_EMPLOYEE_PREFIX + "list";
        //获取缓存
        String json = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(json)) {
            List<EmployeeVO> employeeVOS = JSON.parseArray(json, EmployeeVO.class);
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), employeeVOS);
        }
        //如果没有缓存则查询数据库
        List<EmployeeVO> employeeVOS = employeeService.list().stream().map(s -> {
            DeptVO dept = deptService.getDeptById(s.getDeptId());
            EmployeeVO employeeVO = CopyBeanUtil.copyBean(s, EmployeeVO.class);
            employeeVO.setDeptName(dept.getDeptName());
            return employeeVO;
        }).collect(Collectors.toList());
        //如果没有缓存则添加缓存
        redisCache.setCacheObject(key, employeeVOS, Constant.CACHE_EXPIRE_TTL, TimeUnit.MINUTES);
        return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), employeeVOS);
    }

    @Override
    public Result updateEmployee(EmployeeDTO employeeDTO) {
        boolean flag = employeeService.updateById(CopyBeanUtil.copyBean(employeeDTO, EmployeeDO.class));
        if (flag) {
            redisCache.deleteObject(Constant.CACHE_EMPLOYEE_PREFIX + "list");
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }

    @Override
    public Result deleteEmployee(Long id) {
        boolean flag = employeeService.removeById(id);
        if (flag) {
            redisCache.deleteObject(Constant.CACHE_EMPLOYEE_PREFIX + "list");
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }

    @Override
    public Result addEmployee(EmployeeDTO employeeDTO) {
        boolean flag = employeeService.save(CopyBeanUtil.copyBean(employeeDTO, EmployeeDO.class));
        if (flag) {
            redisCache.deleteObject(Constant.CACHE_EMPLOYEE_PREFIX + "list");
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }

    @Override
    public String selectEmpName(Long id) {
        return employeeMapper.selectEmpName(id);
    }
}
