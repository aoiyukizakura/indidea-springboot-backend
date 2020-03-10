package com.mirai.indidea.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.mirai.indidea.entity.Admin;
import com.mirai.indidea.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtils {
    private static final String USER_ID = "userId";
    private static final String ADMIN_ID = "adminId";


    public static String getToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(user.getPassword()); // 以 password 作为 token 的密钥
        return JWT.create()
//                .withIssuer("xqh") //发行人
//                .withAudience(user.getId()+"") // 将 user id 保存到 token 里面
//                .withExpiresAt(DateUtils.getTime(0.5))  //设置过期时间
                .withClaim(USER_ID, user.getId())
                .sign(algorithm);
    }

    public static String adminToken(Admin admin) {
        Algorithm algorithm = Algorithm.HMAC256("admin");
        return JWT.create().withClaim(ADMIN_ID, admin.getId()).sign(algorithm);
    }

    public static int getIdInRequest(HttpServletRequest request){
        String token = request.getHeader("token");
        return JWT.decode(token).getClaim(USER_ID).asInt();
    }
    public static int getAdminIdInRequest(HttpServletRequest request){
        String token = request.getHeader("token");
        return JWT.decode(token).getClaim(ADMIN_ID).asInt();
    }
}
