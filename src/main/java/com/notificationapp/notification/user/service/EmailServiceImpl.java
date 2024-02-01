package com.notificationapp.notification.user.service;


import com.notificationapp.notification.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration configuration;

    @Override
    public boolean sendMail(User user, Map<String,Object> templateData,String templateName){

        boolean returnMsg = false;

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED,StandardCharsets.UTF_8.name());

            Template template = configuration.getTemplate(templateName);

            String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template,templateData);

            helper.setFrom("legecyprezzie@gmail.com");
            helper.setTo(user.getEmail());
            helper.setText(htmlContent,true);
            helper.setSubject("Email From Notification App");

            mailSender.send(mimeMessage);
            returnMsg = true;

        }catch (Exception e){
            returnMsg = false;
        }

        return returnMsg;
    }

}
