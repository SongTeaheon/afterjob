package com.song.afterjob.controller;

import com.song.afterjob.config.jwt.JwtProperties;
import com.song.afterjob.dto.UserDto;
import com.song.afterjob.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserContoller {

    private final UserService userService;

    @PostMapping(value = "/join", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> join(@RequestBody UserDto userDto){
        String token = userService.join(userDto);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "join success");

        return ResponseEntity.ok().headers(responseHeaders).body(resultMap) ;
    }

    @PostMapping(value = "/login", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> login(@RequestBody UserDto userDto){

        String token;
        try {
            token = userService.login(userDto);
        }catch(Exception e) { //TODO:userId, pw다른 경우 exception 변경하
            return new ResponseEntity<>(userDto.getEmail(), HttpStatus.FORBIDDEN);
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "login success");

        return ResponseEntity.ok().headers(responseHeaders).body(resultMap);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("authorization") String token){
        token = token.replace(JwtProperties.TOKEN_PREFIX, "");
        userService.logout(token);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "logout success");

        return ResponseEntity.ok().body(resultMap);
    }

}
