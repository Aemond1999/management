package com.hya.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.hya.management.common.domain.UserDO;
import com.hya.management.common.pojo.MyUserDetails;
import com.hya.management.mapper.AuthorityMapper;
import com.hya.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<UserDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserDO::getUsername,username);
        UserDO userDO = userService.getOne(lqw);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(userDO)){
            throw new RuntimeException("用户名或密码错误");
        }
        //根据用户查询权限信息 添加到LoginUser中
        List<String> authorities = authorityMapper.getAuthorities(userDO.getId());
        //封装成UserDetails对象返回
        return new MyUserDetails(userDO,authorities);

    }
}
