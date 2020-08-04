package com.song.afterjob.service;

import com.song.afterjob.dto.KakaoInfoDto;
import com.song.afterjob.dto.KakaoTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoApiImpl implements KakaoApi{

    @Value("${kakao.app-key}")
    private String APP_KEY;
    private final RestTemplate restTemplate;

    @Override
    public boolean login(String code) {
        //code : 인증코드
        //1. 리프레시 및 엑세스 토큰 받기
        KakaoTokenDto tokenDto = getToken(code);
        if(tokenDto == null) {
            log.error("KakaoTokenDto is null");
            return false;
        }

        //2. 이 데이터로 kakao email 받기
        KakaoInfoDto kakaoInfo = getKakaoInfo(tokenDto);
        if(kakaoInfo == null) return false;

        //3. kakao email로 로그인 혹은 회원가입하기
        //4. email로 jwt만들기
        //5. jwt리턴


        return true;
    }

    @Override
    public KakaoTokenDto getToken(String code) {
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", APP_KEY);
        map.add("redirect_uri", "http://localhost:8090/users/kakao/login");
        map.add("code", code);

        String url = "https://kauth.kakao.com/oauth/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<KakaoTokenDto> response = restTemplate.postForEntity( url, request , KakaoTokenDto.class );
        if(!response.getStatusCode().is2xxSuccessful()){
            log.error(response.getStatusCode().toString());
            return null;
        }
        if(response.getBody() != null) {
            log.info(response.getBody().toString());
        }
        return response.getBody();
    }

    @Override
    public KakaoInfoDto getKakaoInfo(KakaoTokenDto tokenDto) {

        String url = "https://kapi.kakao.com/v2/user/me";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + tokenDto.getAccess_token());

        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Map.class);

        //todo: 더 깔끔한 변환 찾기
        LinkedHashMap<String, Object> kakaoAccountMap = (LinkedHashMap)response.getBody().get("kakao_account");
        if(kakaoAccountMap == null) {
            log.error("kakaoAccountMap is null");
            return null;
        }
        LinkedHashMap<String, Object> kakaoPropertiesMap = (LinkedHashMap)response.getBody().get("properties");
        if(kakaoPropertiesMap == null) {
            log.error("kakaoPropertiesMap is null");
            return null;
        }
        KakaoInfoDto kakaoInfo = KakaoInfoDto.builder()
                                    .email((String)kakaoAccountMap.get("email"))
                                    .gender((String)kakaoAccountMap.get("gender"))
                                    .build();
        kakaoInfo.setNickname((String)kakaoPropertiesMap.get("nickname"));
        kakaoInfo.setProfileImage((String)kakaoPropertiesMap.get("profile_image"));
        kakaoInfo.setThumbnailImage((String)kakaoPropertiesMap.get("thumbnail_image"));

        return kakaoInfo;
    }
}
