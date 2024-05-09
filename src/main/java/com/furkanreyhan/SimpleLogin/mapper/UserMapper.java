package com.furkanreyhan.SimpleLogin.mapper;

import com.furkanreyhan.SimpleLogin.entity.User;
import com.furkanreyhan.SimpleLogin.entity.UserLogin;
import com.furkanreyhan.SimpleLogin.entity.UserRegister;

import java.util.Base64;

public class UserMapper {
    public static User mapToUser(User user, UserRegister userRegister){
        user.setUsername(userRegister.username());
        user.setEmail(userRegister.email());

        user.setPassword(userRegister.password().hashCode());
        return user;
    }

    public static User mapToUser(User user,UserLogin userLogin){
        user.setUsername(userLogin.getUsername());
        user.setPassword(userLogin.getPassword().hashCode());

        return user;
    }

}
