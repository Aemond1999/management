package com.hya.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.domain.RoleDO;
import com.hya.management.utils.Result;

public interface RoleService extends IService<RoleDO> {

    Result roleList();
}
