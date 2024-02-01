package com.notificationapp.notification.user.service;


import com.notificationapp.notification.user.models.User;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;

@Service
public interface EmailService extends Serializable {

    public boolean sendMail(User user, Map<String,Object> templateData,String templateName);

}
