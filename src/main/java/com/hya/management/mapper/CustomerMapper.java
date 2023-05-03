package com.hya.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.management.common.domain.CustomerDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public  interface CustomerMapper extends BaseMapper<CustomerDO> {
}
