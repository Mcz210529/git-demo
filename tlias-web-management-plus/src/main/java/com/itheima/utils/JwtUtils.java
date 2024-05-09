package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {
    public static String jwt;
    public String genJwt(Map<String, Object> claims) {


      jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, "itheima123123123123112312313122131232131111111111111111111111112")
                .setExpiration(new Date(System.currentTimeMillis() + 3000*1000))
                .compact();
        System.out.println(jwt);
        return jwt;

    }

    public static Claims parseJwt(String jwt) {

        Claims claims = Jwts.parser()
                .setSigningKey("itheima123123123123112312313122131232131111111111111111111111112")
                .parseClaimsJws(jwt)
                .getBody();

        System.out.println(claims);
        return claims;
    }
}
