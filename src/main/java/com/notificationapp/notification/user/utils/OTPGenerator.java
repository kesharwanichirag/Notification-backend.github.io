package com.notificationapp.notification.user.utils;

import java.util.Random;

public class OTPGenerator {
    public static String generateOTP(){
        StringBuffer sbw = new StringBuffer();

        Random random = new Random();

        for(int i=1; i<=6 ; i++){
            sbw.append(random.nextInt(10));
        }

        return sbw.toString();
    }
}
