package com.hya.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.management.common.domian.ProductDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<ProductDO> {
}
