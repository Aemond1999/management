package com.hya.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hya.management.common.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
    Boolean updateAvatar(String avatar,Long id);
    Boolean updateUserRole(Long roleId,Long userId);
    Boolean deleteUserRole(Long id);
    Boolean addUserRole(Long userId,Long roleId);
}
