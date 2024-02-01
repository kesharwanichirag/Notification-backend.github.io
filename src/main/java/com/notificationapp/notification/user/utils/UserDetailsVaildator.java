package com.notificationapp.notification.user.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDetailsVaildator {
    Pattern pattern = null;
    Matcher matcher = null;

    public boolean userNameValidator(String userName){
        boolean flag = true;

        String userNamePattern = "^[a-zA-Z][a-zA-Z0-9]{5,19}$";
        pattern = Pattern.compile(userNamePattern);

        matcher = pattern.matcher(userName);
        if(!matcher.matches()){
            flag = false;
        }

        return flag;
    }

    public boolean emailValidator(String email){
        boolean flag = true;

        String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        pattern = Pattern.compile(emailPattern);

        matcher = pattern.matcher(email);
        if(!matcher.matches()){
            flag = false;
        }

        return flag;
    }

    public boolean firstNameValidator(String firstName){
        boolean flag = true;
        String firstNamePattern = "^[a-zA-Z]{1,19}$";
        pattern = Pattern.compile(firstNamePattern);

        matcher = pattern.matcher(firstName);
        if(!matcher.matches()){
            flag = false;
        }

        return flag;
    }

    public boolean lastNameValidator(String lastName){
        boolean flag = true;
        String lastNamePattern = "^[a-zA-Z]{1,19}$";
        pattern = Pattern.compile(lastNamePattern);

        matcher = pattern.matcher(lastName);
        if(!matcher.matches()){
            flag = false;
        }

        return flag;
    }
}
