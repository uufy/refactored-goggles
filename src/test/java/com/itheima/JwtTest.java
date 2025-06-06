package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    //生成jwt令牌
    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "aXRoaWVtYQ==")//指定加密算法和密钥
                .addClaims(dataMap)//添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置过期时间
                .compact();//生成jwt令牌
        System.out.println(jwt);

    }


    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc0ODQyMzY2NH0.pIU83rkU8gPJcnjWbMOGR4cEsmnXAN8P02nJ_ZwKiQg";


        Claims body = Jwts.parser().setSigningKey("aXRoaWVtYQ==")
                .parseClaimsJws(token)
                .getBody();
        System.out.println(body);
    }


}
