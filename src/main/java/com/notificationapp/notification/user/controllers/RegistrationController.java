package com.notificationapp.notification.user.controllers;

import com.notificationapp.notification.user.models.User;
import com.notificationapp.notification.user.service.EmailService;
import com.notificationapp.notification.user.service.UserService;
import com.notificationapp.notification.user.utils.OTPGenerator;
import com.notificationapp.notification.user.utils.UserDetailsVaildator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public String createUser(@RequestBody User user){

        //List to store the return Messages
        String returnMessage = null;

        //Util Class to validate User Details
        UserDetailsVaildator userDetailsVaildator = new UserDetailsVaildator();

        //Validate Variables
        boolean userNameVaildatorResponse = true;
        boolean emailaildatorResponse = true;
        boolean firstNameVaildatorResponse = true;
        boolean lastNameVaildatorResponse = true;

        try{

            //Validating UserName
            userNameVaildatorResponse = userDetailsVaildator.userNameValidator(user.getUserName());
            if(userNameVaildatorResponse == false){
                returnMessage= "UserName is not Valid";
            }

            //Validating email
            emailaildatorResponse = userDetailsVaildator.emailValidator(user.getEmail());
            if(emailaildatorResponse == false){
                returnMessage = "Email is not Valid";
            }

            //Validating firstName
            firstNameVaildatorResponse = userDetailsVaildator.firstNameValidator(user.getFirstName());
            if(firstNameVaildatorResponse == false){
                returnMessage = "FirstName is not Valid";
            }

            //Validating lastName
            lastNameVaildatorResponse = userDetailsVaildator.lastNameValidator(user.getLastName());
            if(lastNameVaildatorResponse == false){
                returnMessage = "LastName is not Valid";
            }

            //If Every Validator is True Then If will run otherwise else will run
            if(userNameVaildatorResponse == true && emailaildatorResponse == true && firstNameVaildatorResponse == true && lastNameVaildatorResponse == true){

                String otp = OTPGenerator.generateOTP();

                //User will be locked after submit a form
                user.setLocked(1);
                user.setOtp(otp);

                //if user will be saved then send a mail
                if(userService.saveUser(user).equals("User Saved")){

                    Map<String,Object> templateData = new HashMap<String,Object>();

                    templateData.put("userName",user.getUserName());
                    templateData.put("OtpMessage",otp);

                    String templateName = "otp.ftl";

                    //send mail if everything is ok
                    if(emailService.sendMail(user,templateData,templateName)){

                        returnMessage = "User Created & Mail Sent";


                    }else {
                        returnMessage = "User Created & Failed to send Mail";
                    }
                }else {
                    returnMessage= "User Not Created";
                }
            }else {
                returnMessage= "Validation Errors";
            }
        }catch (Exception e){
            returnMessage = "Exception";
        }

        return returnMessage;
    }

    //method for OTP Verification
    @PostMapping("/otp")
    public String verifyOTP(@RequestBody String otp) {

        String returnValue =null;

        try{
            User user = userService.getUserByOTP(otp);

            if(user != null){
                if(otp.equals(user.getOtp())){

                    userService.updateLocked(user);

                    returnValue = "OTP Verified";

                    Map<String,Object> templateData = new HashMap<String,Object>();

                    templateData.put("OtpMessage",otp);
                    templateData.put("userName", user.getUserName());

                    String templateName = "welcome.ftl";
                    if(emailService.sendMail(user,templateData,templateName)) {
                        returnValue = "OTP Verified & Mail Sent";

                        user.setOtp(null);
                    }else{
                        returnValue = "Mail not sent";
                    }
                }
            }else{
                returnValue = "Wrong OTP";
            }

        }catch (Exception e){
            e.printStackTrace();
            returnValue = "Exception";
        }
        return returnValue;
    }

    //method for Resend OTP
    @GetMapping("/resendotp")
    public String resendOTP(HttpServletRequest request) {

        String returnValue = null;

        //session obj
        HttpSession session = request.getSession();

        try{
            //expire resent otp then do set new OTP there
            session.removeAttribute("otp");

            //getting session attribute here as user object
            User userSession = (User) session.getAttribute("user");

            //if user Object found on session
            if(userSession != null){
                String otp = OTPGenerator.generateOTP();

                Map<String,Object> templateData = new HashMap<String,Object>();

                templateData.put("userName",userSession.getUserName());
                templateData.put("OtpMessage",otp);

                String templateName = "otp.ftl";

                //we will send a mail here
                if(emailService.sendMail(userSession,templateData,templateName)){

                    session.setAttribute("otp",otp);

                    returnValue = "OTP & Mail Sent";
                }else {
                    returnValue = "Mail not sent";
                }
            }else {
                returnValue = "User Not Found";
            }

        }catch (Exception e){
            returnValue = "Exception";
        }

        return returnValue;
    }
}
