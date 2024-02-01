package com.notificationapp.notification.user.service;


import com.notificationapp.notification.user.models.User;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public interface UserService extends Serializable {

	public abstract String saveUser( User user);

	public abstract String updateLocked(User user);

	public abstract User getUserByOTP(String otp);
}
