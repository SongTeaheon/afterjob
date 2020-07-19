package com.song.afterjob.service;

import com.song.afterjob.config.jwt.JwtProvider;
import com.song.afterjob.domain.UserEntity;
import com.song.afterjob.repository.UserRepository;
import com.song.afterjob.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public UserDto save(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return modelMapper.map(userRepository.save(userEntity), UserDto.class);
    }

    @Override
    public String login(UserDto userDto) {
        UserEntity userEntity = userRepository.findByUserId(userDto.getUserId());
        if(userEntity == null) {
            log.error("ID가 틀렸습니다.");
            throw new UsernameNotFoundException("ID가 틀렸습니다.");
        }
        if(!passwordEncoder.matches(userDto.getPassword(), userEntity.getPassword())) {
            log.error("비밀번호가 틀렸습니다.");
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return jwtProvider.createToken(userEntity.getUsername(), userEntity.getRoleList(), userEntity.getAuthorityList());
    }

    @Override
    public String join(UserDto userDto) {

        if(userRepository.countByUserId(userDto.getUserId()) == 1) {
            log.error("already exist in UserService");
            throw new IllegalArgumentException("이미 존재하는 ID입니다.");
        }
        UserEntity userEntitySave = UserEntity.builder()
                .userId(userDto.getUserId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles("ROLE_USER")
                .authorities("")
                .build();
        UserEntity userEntity = userRepository.save(userEntitySave);
        return jwtProvider.createToken(userEntity.getUsername(), userEntity.getRoleList(), userEntity.getAuthorityList());

    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}
