//package com.hya.management.filter;
//import com.alibaba.fastjson.JSON;
//import com.hya.management.utils.JwtUtil;
//import com.hya.management.utils.RedisCache;
//import io.jsonwebtoken.Claims;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class TokenFilter extends OncePerRequestFilter {
//    @Autowired
//    RedisCache redisCache;
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull FilterChain filterChain) throws ServletException, IOException {
//       //获取token
//        String token = httpServletRequest.getHeader("Authorization");
//        //如果没有就放行
//        if (!StringUtils.hasText(token)){
//            filterChain.doFilter(httpServletRequest,httpServletResponse);
//            return;
//        }
//        //解析token
//        String user;
//        UserInTokenBO userInTokenBO;
//        try {
//            Claims claims = JwtUtil.parseJWT(token);
//            user = claims.getSubject();
//            userInTokenBO = JSON.parseObject(user, UserInTokenBO.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("非法token");
//        }
//       MyUserDetails userDetails= redisCache.getCacheObject("login:"+ userInTokenBO.getUsername());
//        if (userDetails==null){
//            throw new RuntimeException("用户未登录");
//        }
//        //获取权限信息
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,null);
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        filterChain.doFilter(httpServletRequest,httpServletResponse);
//    }
//}
