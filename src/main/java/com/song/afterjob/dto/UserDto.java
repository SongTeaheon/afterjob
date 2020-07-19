package com.song.afterjob.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String userId;
    private String password;
    private String roles;
    private String authorities;

    public UserDto(){}
    public List<String> getRoleList() {
        if(this.roles == null)
            return new ArrayList<>();
        return Arrays.asList(roles.split(","));
    }

    public List<String> getAuthorityList() {
        if(this.authorities == null){
            return new ArrayList<>();
        }
        return Arrays.asList(authorities.split(","));
    }

}
