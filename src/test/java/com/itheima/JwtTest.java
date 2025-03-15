package com.itheima;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "Lebron");
        // 生成 JWT 的代码
        String token = JWT.create()
                .withClaim("user", claims) // 添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // 添加过期时间
                .sign(Algorithm.HMAC256("itheima")); // 指定算法，配置密钥

        System.out.println(token);
    }

    @Test
    public void testParse() {
        // 定义字符串，模拟用户传递过来的 token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IkxlYnJvbiJ9LCJleHAiOjE3NDA1Mzg5NjB9" +
                ".oeWFPV35-2OtxmWpWwK8hXvvvf1G3ACFZ7rJn5pDKrg";

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("itheima")).build();

        // 验证 token，生成一个解析后的 JWT 对象
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));

        // 如果篡改了头部和载荷部分的数据，则验证失败
        // 如果篡改了密钥，则验证失败
        // 如果 token 过期，则验证失败
    }

}
