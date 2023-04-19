package com.hya.management.handler;

import com.alibaba.fastjson.JSON;
import com.hya.management.enums.HttpCodeEnum;
import com.hya.management.utils.Result;
import com.hya.management.utils.WebUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    //授权异常处理器
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
       Result result = Result.failResult(HttpCodeEnum.NO_OPERATOR_AUH.getCode(), HttpCodeEnum.NO_OPERATOR_AUH.getMsg());
        String s = JSON.toJSONString(result);
        WebUtil.renderString(httpServletResponse,s);
    }
}
