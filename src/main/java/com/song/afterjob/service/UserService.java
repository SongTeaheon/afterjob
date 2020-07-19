package com.song.afterjob.service;

import com.song.afterjob.domain.UserEntity;
import com.song.afterjob.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);
    String login(UserDto userDto);
    String join(UserDto userDto);
    List<UserEntity> findAll();
}
