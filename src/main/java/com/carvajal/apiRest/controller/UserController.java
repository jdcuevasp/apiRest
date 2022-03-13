package com.carvajal.apiRest.controller;

import com.carvajal.apiRest.dto.ResponseDto;
import com.carvajal.apiRest.dto.SignInDto;
import com.carvajal.apiRest.dto.SignInResponseDto;
import com.carvajal.apiRest.dto.SignUpDto;
import com.carvajal.apiRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody SignUpDto signUpDto){
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInResponseDto signInDto(@RequestBody SignInDto signInDto) throws Exception {
        return userService.signIn(signInDto);
    }
}
