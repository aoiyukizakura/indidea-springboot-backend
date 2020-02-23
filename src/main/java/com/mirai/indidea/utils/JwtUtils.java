package com.mirai.indidea.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mirai.indidea.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtils {
    private static final String USER_ID = "userId";


    public static String getToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(user.getPassword()); // 以 password 作为 token 的密钥
        return JWT.create()
//                .withIssuer("xqh") //发行人
//                .withAudience(user.getId()+"") // 将 user id 保存到 token 里面
//                .withExpiresAt(DateUtils.getTime(0.5))  //设置过期时间
                .withClaim(USER_ID, user.getId())
                .sign(algorithm);
    }

    public static int getIdInRequest(HttpServletRequest request){
        String token = request.getHeader("token");
        return JWT.decode(token).getClaim(USER_ID).asInt();
    }
}
