package com.furkanreyhan.SimpleLogin.controller;

import com.furkanreyhan.SimpleLogin.entity.ResponseDto;
import com.furkanreyhan.SimpleLogin.entity.User;
import com.furkanreyhan.SimpleLogin.entity.UserLogin;
import com.furkanreyhan.SimpleLogin.entity.UserRegister;
import com.furkanreyhan.SimpleLogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register-user")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserRegister userRegister){

        userService.saveUser(userRegister);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(),"Kayit Basarili"));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody UserLogin userLogin) {
        userService.authenticateUser(userLogin);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(), "Login Basarili"));
    }

}
