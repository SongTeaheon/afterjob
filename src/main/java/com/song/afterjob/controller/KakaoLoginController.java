package com.song.afterjob.controller;

import com.song.afterjob.service.KakaoApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/users/kakao")
@RequiredArgsConstructor
@Slf4j
public class KakaoLoginController {

    private final KakaoApi kakaoApi;

    @GetMapping("/login")
    public void loginWithKakao(@RequestParam("code") String code){
        log.info("loginWithKakao code : " + code);

        kakaoApi.login(code);
    }

}
