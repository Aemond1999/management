package com.hya.management.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.bo.UserTokenBO;
import com.hya.management.common.domian.EmployeeDO;
import com.hya.management.common.domian.UserDO;
import com.hya.management.common.dto.UserInfoDTO;
import com.hya.management.common.dto.UserLoginDTO;
import com.hya.management.common.pojo.MyUserDetails;
import com.hya.management.common.vo.DeptVO;
import com.hya.management.common.vo.EmployeeVO;
import com.hya.management.common.vo.UserInfoVO;
import com.hya.management.constant.Constant;
import com.hya.management.enums.HttpCodeEnum;
import com.hya.management.mapper.UserMapper;
import com.hya.management.service.DeptService;
import com.hya.management.service.EmployeeService;
import com.hya.management.service.UserService;
import com.hya.management.utils.CopyBeanUtil;
import com.hya.management.utils.JwtUtil;
import com.hya.management.utils.RedisCache;
import com.hya.management.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //登录
    @Override
    public Result login(UserLoginDTO userLoginDTO) {
        //封装Authentication对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        //调用authenticate进行认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)) {
            return Result.failResult(HttpCodeEnum.LOGIN_ERROR.getCode(),HttpCodeEnum.LOGIN_ERROR.getMsg());
        }
        //获取authentication中封装的用户信息
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        UserTokenBO userTokenBO = CopyBeanUtil.copyBean(userDetails.getUserDO(), UserTokenBO.class);
        //获取职工名
        String empName = employeeService.selectEmpName(userTokenBO.getEmpId());
        userTokenBO.setEmpName(empName);
        String json = JSON.toJSONString(userTokenBO);
        //使用userid和name生成token
        String jwt = JwtUtil.createJWT(json);
        redisCache.setCacheObject("login:" + userTokenBO.getUsername(), userDetails);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), map);
    }

    //注册用户
    @Override
    public Result register(@NotNull UserDO userDO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(userDO.getPassword());
        userDO.setPassword(password);
        return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), userService.save(userDO));
    }

    //注销用户
    @Override
    public Result logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        String userId = userDetails.getUserDO().getUsername();
        redisCache.deleteObject("login:" + userId);
        return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
    }

    //展示用户个人信息
    @Override
    public Result getUserInfoById(Long id) {
        String key = Constant.CACHE_USER_PREFIX + id;
        String json = stringRedisTemplate.opsForValue().get(key);

        if (StringUtils.isNotBlank(json)) {
            UserInfoVO userInfoVO = JSON.parseObject(json, UserInfoVO.class);
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), userInfoVO);
        }
        UserDO user = userService.getById(id);
        //如果数据库中没有该对象则返回空缓存
        if (user == null) {
            redisCache.setCacheObject(key, "", Constant.CACHE_EXPIRE_TTL, TimeUnit.MINUTES);
            return Result.failResult(HttpCodeEnum.NOT_ACCEPTABLE.getCode(), HttpCodeEnum.NOT_ACCEPTABLE.getMsg());
        }
        EmployeeDO employeeDO = employeeService.getById(user.getEmpId());
        EmployeeVO employeeVO = CopyBeanUtil.copyBean(employeeDO, EmployeeVO.class);
        DeptVO deptVO= deptService.getDeptById(employeeVO.getDeptId());
        employeeVO.setDeptName(deptVO.getDeptName());
        UserInfoVO userInfoVO =new UserInfoVO(user.getId(), user.getUsername(),employeeVO);
        redisCache.setCacheObject(key, userInfoVO, Constant.CACHE_EXPIRE_TTL, TimeUnit.MINUTES);
        return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), userInfoVO);
    }

    @Override
    public Result updateUserInfo(UserInfoDTO userInfoDTO) {
        boolean flag = userService.updateById(CopyBeanUtil.copyBean(userInfoDTO, UserDO.class));
        if (flag) {
            redisCache.deleteObject(Constant.CACHE_USER_PREFIX + userInfoDTO.getId());
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }


    @Override
    public Result updateAvatar(String avatar, Long id) {
        boolean flag = userMapper.updateAvatar(avatar, id);
        if (flag) {
            redisCache.deleteObject(Constant.CACHE_USER_PREFIX + id);
            return Result.okResult(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
        } else {
            return Result.failResult(HttpCodeEnum.FAIL.getCode(), HttpCodeEnum.FAIL.getMsg());
        }
    }


    //获取用户名和ID
    @Override
    public UserTokenBO getUserById(Long id) {
        LambdaQueryWrapper<UserDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserDO::getStatus, Constant.USER_STATUS_NORMAL).eq(UserDO::getId, id);
        UserDO userDO = userService.getOne(lqw);
        return CopyBeanUtil.copyBean(userDO, UserTokenBO.class);
    }


}
