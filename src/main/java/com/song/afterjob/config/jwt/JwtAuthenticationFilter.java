package com.song.afterjob.config.jwt;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
* UsernamePasswordAuthentication Filter전에 사용될 JwtAuthenticationFilter
* */
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtManager jwtManager;
    private final RedisTemplate<String, Boolean> redisTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtManager.resolveToken((HttpServletRequest)servletRequest);

        if(token != null) {
            token = token.replace(JwtProperties.TOKEN_PREFIX, "");

            if(redisTemplate.opsForValue().get(token) != null){
                //로그아웃 블랙리스트 체
                log.error("로그아웃된 토큰입니다.");
            }else if(jwtManager.isValidToken(token)){
                Authentication auth = jwtManager.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }else{
                log.info("유효하지 않은 토큰입니다.");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
