package com.hya.management.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.bo.UserTokenBO;
import com.hya.management.common.domain.UserDO;
import com.hya.management.common.dto.UserDTO;
import com.hya.management.common.dto.UserInfoDTO;
import com.hya.management.common.dto.UserLoginDTO;
import com.hya.management.utils.Result;

public interface UserService extends IService<UserDO> {
    Result userList();
    Result login(UserLoginDTO userLoginDTO);
    UserTokenBO getUserById(Long id);
    Result logout();
    Result getUserInfoById(Long id);
    Result updateUserInfo(UserInfoDTO userInfoDTO);
    Result updateAvatar(String avatar,Long id);

    Result updateUser(UserDTO userDTO);
    Result deleteUser(Long id);
    Result addUser(UserDTO userDTO);






}
