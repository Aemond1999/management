package com.hya.management.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hya.management.common.pojo.MyUserDetails;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义元数据对象处理器
 * 自动填充配置
 */
@Component
public class MyMetaObjectHandle implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
       metaObject.setValue("createBy", userDetails.getUserDO().getId());
      metaObject.setValue("updateBy",userDetails.getUserDO().getId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateBy",userDetails.getUserDO().getId());
        metaObject.setValue("useTime", LocalDateTime.now());
        metaObject.setValue("useBy",userDetails.getUserDO().getId());
    }
}
