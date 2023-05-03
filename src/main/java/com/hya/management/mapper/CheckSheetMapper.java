package com.hya.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.management.common.domain.CheckSheetDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface CheckSheetMapper extends BaseMapper<CheckSheetDO> {
    Boolean audit(Long id);
    Boolean updateCheckTimeAndStatus(LocalDateTime time, Long id);
}
