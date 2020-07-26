package com.song.afterjob.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtManager {

    private final UserDetailsService userDetailsService;
    public final PasswordEncoder passwordEncoder;
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(JwtProperties.HEADER_STRING);
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserPk(String token) {
        Claims claims = Jwts.parser().setSigningKey(JwtProperties.SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean isValidToken(String token) {
        if(token.isEmpty()){
            return false;
        }
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(JwtProperties.SECRET_KEY)
                .parseClaimsJws(token);
        return !jws.getBody().getExpiration().before(new Date());
    }

    public Date getExpiration(String token){
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(JwtProperties.SECRET_KEY)
                .parseClaimsJws(token);
        return jws.getBody().getExpiration();
    }
}
