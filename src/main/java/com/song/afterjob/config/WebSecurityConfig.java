package com.song.afterjob.config;

import com.song.afterjob.config.jwt.JwtAuthenticationFilter;
import com.song.afterjob.config.jwt.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtManager jwtManager;
    private final RedisTemplate<String, Boolean> redisTemplate;
    // authenticationManager를 Bean 등록합니다.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() //rest api만을 고려
                .csrf().disable() //jwt에서 csrf는 고려대상이 아님.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//세션 유지x
                .and()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/posts/list/**").permitAll()
                .antMatchers("/posts/pagingList/**").permitAll()
                .antMatchers("/posts/*").hasRole("USER") //읽기 쓰기 수정 삭제
                .anyRequest().permitAll() //아직은 안만들었음!
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtManager, redisTemplate), UsernamePasswordAuthenticationFilter.class);
    }
}
