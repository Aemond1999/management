package com.hya.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.management.common.domian.DeptDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptMapper extends BaseMapper<DeptDO> {
}
