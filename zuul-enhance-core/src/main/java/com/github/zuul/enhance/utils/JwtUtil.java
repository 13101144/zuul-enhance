package com.github.zuul.enhance.utils;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    /**
     * 签名用的密钥
     */
    private static final String SIGNING_KEY = "abc";

    /**
     * 用户登录成功后生成Jwt
     * 使用Hs256算法
     *
     * @param exp jwt过期时间
     * @param claims 保存在Payload（有效载荷）中的内容
     * @return token字符串
     */
    public static String createJWT(Date exp, Map<String, Object> claims) {
        //指定签名的时候使用的签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //创建一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                //保存在Payload（有效载荷）中的内容
                .setClaims(claims)
                //iat: jwt的签发时间
                .setIssuedAt(now)
                //设置过期时间
                .setExpiration(exp)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, SIGNING_KEY);

        return builder.compact();
    }

    /**
     * 解析token，获取到Payload（有效载荷）中的内容，包括验证签名，判断是否过期
     *
     * @param token
     * @return
     */
    public static Claims parseJWT(String token) {
        //得到DefaultJwtParser
        Claims chaims = Jwts.parser().setSigningKey("abc".getBytes())
                .parseClaimsJws(token).getBody();
        return chaims;
    }

    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        try {
            return parseJWT(token)
                    .getExpiration()
                    .before(new Date());
        } catch (ExpiredJwtException ex) {
            return true;
        }
    }

    public static void main(String[] args) {
        Claims claims  =  JwtUtil.parseJWT("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTc4MDcwOTQsInVzZXJfbmFtZSI6Im1hY3JvIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiMjE2YTY5MWEtNjVkZS00NGRjLWIzMjQtMzMwNmE3OWQ0ODhlIiwiY2xpZW50X2lkIjoiYWRtaW4iLCJzY29wZSI6WyJhbGwiXX0.cxJXpyR6CBPXnPHBKG53ohFJsll8gBh_OqGeZEdopCo");
        System.out.println(claims);
    }

}
