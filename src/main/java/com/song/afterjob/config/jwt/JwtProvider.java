package com.song.afterjob.config.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@Slf4j
public class JwtProvider {

    public String createToken(String userId, List<String> roles, List<String> authorities) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles", roles);
        claims.put("authorities", authorities);

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + JwtProperties.TOKEN_VALID_TIME)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, JwtProperties.SECRET_KEY)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }


}
