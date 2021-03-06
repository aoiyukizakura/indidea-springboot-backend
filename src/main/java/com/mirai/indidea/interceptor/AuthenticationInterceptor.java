package com.mirai.indidea.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.mirai.indidea.annotation.AdminToken;
import com.mirai.indidea.annotation.PassToken;
import com.mirai.indidea.annotation.UserLoginToken;
import com.mirai.indidea.entity.Admin;
import com.mirai.indidea.entity.User;
import com.mirai.indidea.service.AdminService;
import com.mirai.indidea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passToken注释，有则跳过认证
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
                    throw new RuntimeException("无效token，请重新登录");
                }
                // 获取 token 中的 user id
                Integer userId;
                try {
//                    userId = JWT.decode(token).getAudience().get(0);
                    userId = JWT.decode(token).getClaims().get("userId").asInt();
//                    System.out.println(userId);

                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401 Unauthorized");
                }
                User user = userService.find(userId);
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
                JWTVerifier jwtVerifier = JWT.require(algorithm)
//                        .withIssuer("xqh")
                        .build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401 Unauthorized");
                }
                return true;
            }
        }
        //检查是否需要管理员权限
        if (method.isAnnotationPresent(AdminToken.class)) {
            AdminToken adminToken = method.getAnnotation(AdminToken.class);
            if(adminToken.required()) {
                if (token == null) {
                    throw new RuntimeException("无效token");
                }
                int adminId;
                try {
                    Claim claim = JWT.decode(token).getClaims().get("adminId");
                    if (claim == null) {
                        throw new RuntimeException("401 Unauthorized");
                    } else {
                        adminId = claim.asInt();
                    }
                } catch (JWTDecodeException e) {
                    throw new RuntimeException("401 Unauthorized");
                }
                Admin admin = adminService.find(adminId);
                if(admin == null) {
                    throw new RuntimeException("找不到该管理员，请重新再试");
                }
                Algorithm algorithm = Algorithm.HMAC256("admin");
                JWTVerifier verifier = JWT.require(algorithm).build();
                try {
                    verifier.verify(token);
                } catch (JWTDecodeException e) {
                    throw new RuntimeException("401 Unauthorized");
                }
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
