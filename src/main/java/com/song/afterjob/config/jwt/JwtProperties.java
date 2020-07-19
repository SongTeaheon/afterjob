package com.song.afterjob.config.jwt;

import java.util.Base64;

public class JwtProperties {

    public static final String SECRET_KEY = "sosong";
    //public static String SECRET_KEY = Base64.getEncoder().encodeToString(JwtProperties.SECRET_KEY_ORIGIN.getBytes());
    public static final long TOKEN_VALID_TIME = 30 * 60 * 1000L;// 토큰 유효시간 30분
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
