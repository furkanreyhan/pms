package com.furkanreyhan.SimpleLogin.service;

import com.furkanreyhan.SimpleLogin.entity.UserLogin;
import com.furkanreyhan.SimpleLogin.entity.UserRegister;
import com.furkanreyhan.SimpleLogin.exception.UnauthorizedException;
import com.furkanreyhan.SimpleLogin.exception.UserNotFoundException;
import com.furkanreyhan.SimpleLogin.entity.User;
import com.furkanreyhan.SimpleLogin.mapper.UserMapper;
import com.furkanreyhan.SimpleLogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void saveUser(UserRegister userRegister) {
        User user = UserMapper.mapToUser(new User(),userRegister);
        userRepository.save(user);

    }

    public boolean authenticateUser(UserLogin userLogin) {
        Optional<User> userOptional = userRepository.findByUsername(userLogin.username());
        User user;
        if (userOptional.isPresent()){
            user = userOptional.get();
            return validatePassword(user,userLogin);
        }
        else {
            //kullanıcı bulunamadı
            throw new UserNotFoundException("Kullanici Bulunamadi");
        }
    }
    public boolean validatePassword(User user,UserLogin userLogin){
        if( user.getPassword() != userLogin.password().hashCode()) {
            System.out.println("Sifre yanlis");
            throw new UnauthorizedException("Sifre yanlis");
        }
        return true;
    }


}
