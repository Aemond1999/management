package com.hya.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.domain.AuthorityDO;

public interface AuthorityService extends IService<AuthorityDO> {

    String getAuthorities(Long id);
}
