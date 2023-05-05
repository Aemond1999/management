package com.hya.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.management.common.domain.AuthorityDO;
import com.hya.management.mapper.AuthorityMapper;
import com.hya.management.service.AuthorityService;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, AuthorityDO> implements AuthorityService {


    @Override
    public String getAuthorities(Long id) {
        return null;
    }
}
