package com.song.afterjob.service;

import com.song.afterjob.dto.KakaoInfoDto;
import com.song.afterjob.dto.KakaoTokenDto;

public interface KakaoApi {
    boolean login(String code);
    KakaoTokenDto getToken(String code);
    KakaoInfoDto getKakaoInfo(KakaoTokenDto tokenDto);
}
