package com.jackfrank.server.config.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : jackfrank
 * @version : v1.0
 * @description : JwtToken工具类
 * @createTime : 2022/8/28 16:03
 */
@Slf4j
@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
    * @Description: 根据用户信息生成Token
    * @Param: [userDetails]
    * @return: java.lang.String
    * @Author: jackfrank
    * @Date: 2022/8/28 16:21
    */
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>(16);
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());

        return generateToken(claims);
    }

    /**
    * @Description: 从Token中获取登录用户名
    * @Param: [token]
    * @return: java.lang.String
    * @Author: jackfrank
    * @Date: 2022/8/28 16:32
    */
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }

        return username;
    }

    /**
    * @Description: 验证Token是否有效
    * @Param: [token, userDetails]
    * @return: boolean
    * @Author: jackfrank
    * @Date: 2022/8/28 16:47
    */
    public boolean validateToken(String token, UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && isTokenExpired(token);
    }

    /**
    * @Description: 判断Token是否可以被刷新
    * @Param: [token]
    * @return: boolean
    * @Author: jackfrank
    * @Date: 2022/8/28 16:55
    */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
    * @Description: 刷新Token
    * @Param: [token]
    * @return: java.lang.String
    * @Author: jackfrank
    * @Date: 2022/8/28 16:58
    */
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());

        return generateToken(claims);
    }

    //*************************************************     分割线     *****************************************************************//

    /**
    * @Description: 根据荷载生成Token
    * @Param: [claims]
    * @return: java.lang.String
    * @Author: jackfrank
    * @Date: 2022/8/28 16:27
    */
    private String generateToken(Map<String, Object> claims){
        return Jwts.builder().setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.ES512, secret)
                .compact();
    }

    /**
    * @Description: 生成Token失效时间
    * @Param: []
    * @return: java.util.Date
    * @Author: jackfrank
    * @Date: 2022/8/28 16:29
    */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
    * @Description: 从Token中获取荷载
    * @Param: [token]
    * @return: io.jsonwebtoken.Claims
    * @Author: jackfrank
    * @Date: 2022/8/28 16:36
    */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e){
            claims = null;
            log.info("JWT格式验证失败:{}", token);
        }

        return claims;
    }

    /**
    * @Description: 判断Token是否失效
    * @Param: [token]
    * @return: boolean
    * @Author: jackfrank
    * @Date: 2022/8/28 16:48
    */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
    * @Description: 从Token获取过期时间
    * @Param: [token]
    * @return: java.util.Date
    * @Author: jackfrank
    * @Date: 2022/8/28 16:52
    */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expiredDate = claims.getExpiration();

        return expiredDate;
    }
}
