package com.hya.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.domian.ProviderDO;
import com.hya.management.common.dto.CustomerDTO;
import com.hya.management.common.dto.ProviderDTO;
import com.hya.management.utils.Result;

public interface ProviderService extends IService<ProviderDO> {
    Result providerList();
    Result updateProvider(ProviderDTO providerDTO);
    Result deleteProvider(Long id);

    Result addProvider(  ProviderDTO providerDTO);
}
