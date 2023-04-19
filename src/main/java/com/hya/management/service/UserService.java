package com.hya.management.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.bo.UserTokenBO;
import com.hya.management.common.domian.UserDO;
import com.hya.management.common.dto.UserInfoDTO;
import com.hya.management.common.dto.UserLoginDTO;
import com.hya.management.utils.Result;

public interface UserService extends IService<UserDO> {
    Result login(UserLoginDTO userLoginDTO);
    Result register(UserDO userDO);
    UserTokenBO getUserById(Long id);
    Result logout();
    Result getUserInfoById(Long id);
    Result updateUserInfo(UserInfoDTO userInfoDTO);
    Result updateAvatar(String avatar,Long id);




}
