package com.hya.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.management.common.domain.AuthorityDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorityMapper extends BaseMapper<AuthorityDO> {
    List<String> getAuthorities(Long id);
}
