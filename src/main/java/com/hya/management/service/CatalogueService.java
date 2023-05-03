package com.hya.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.domain.CatalogueDO;
import com.hya.management.utils.Result;

public interface CatalogueService extends IService<CatalogueDO> {
    Result catalogueListByProvId(Long id);
}
