package com.song.afterjob.dto;

import lombok.Data;

@Data
public class KakaoTokenDto {
    private String token_type;
    private String access_token;
    private int expires_in;
    private String refresh_token;
    private int refresh_token_expires_in;

}
