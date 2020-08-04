package com.song.afterjob.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoInfoDto {
    private String nickname;
    private String profileImage;
    private String thumbnailImage;
    private String email;
    private String gender;
}
