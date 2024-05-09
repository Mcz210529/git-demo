package com.itheima;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class TliasWebManagementApplicationTests {
    String jwt;

    //JWT获取
    @Test
    public void genJwt() {


        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "tom");
        jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, "mcz1234567890123456789012345678901234567890")
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000 * 12))
                .compact();
        System.out.println(jwt);
        Claims result = Jwts.parser()
                .setSigningKey("mcz1234567890123456789012345678901234567890")
                .parseClaimsJws(jwt)
                .getBody();
        System.out.println(result);

    }

    //JWT解密
    @Test
    public void parseJwt() {
        System.out.println(jwt);


    }
}
