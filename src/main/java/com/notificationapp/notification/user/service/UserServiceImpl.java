package com.notificationapp.notification.user.service;

import com.notificationapp.notification.user.models.User;
import com.notificationapp.notification.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public String saveUser(User user) {

        User userObj = userRepository.save(user);

        return "User Saved";
    }

    @Override
    public User getUserByOTP(String otp){
        User userObj = userRepository.findByOtp(otp);

        return userObj;
    }

    @Override
    public String updateLocked(User user){
        User userObj = userRepository.findById(user.getUserId()).orElse(null);

        userObj.setLocked(0);

        userRepository.save(userObj);

        return "locked updated";

    }
}
