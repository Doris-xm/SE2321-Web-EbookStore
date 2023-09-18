package com.example.ebook_back.interceptor;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.ebook_back.annotation.PassToken;
import com.example.ebook_back.annotation.UserLoginToken;
import com.example.ebook_back.entity.UserAuth;
import com.example.ebook_back.serviceImpl.TokenServiceImpl;
import com.example.ebook_back.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * @author jinbin
 * @date 2018-07-08 20:41
 */
@Configuration
public class AuthenticInterceptor implements HandlerInterceptor {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    TokenServiceImpl tokenService;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        String uri = httpServletRequest.getRequestURI();
        System.out.println(uri);
        if (uri.contains("/login") || uri.contains("/resign")) {
            return true; // 放行登录接口
        }

        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                UserAuth user = userService.findUserAuthById(Integer.parseInt(userId));
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserName() + user.getPassword()))
                        .withClaim("user_id", user.getUserId())
                        .withClaim("username", user.getUserName())
                        .build();
                // check if token expire
                if(jwtVerifier.verify(token).getExpiresAt().before(new java.util.Date())){
                    throw new RuntimeException("token expired");
                }

                //search redis to check if token in blacklist
//                if(redisTemplate.opsForValue().get("jwt_" + token) != null){
//                    throw new RuntimeException("token xxx expired");
//                }

                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                httpServletRequest.setAttribute("user_id", userId);
                httpServletRequest.setAttribute("username", user.getUserName());
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
