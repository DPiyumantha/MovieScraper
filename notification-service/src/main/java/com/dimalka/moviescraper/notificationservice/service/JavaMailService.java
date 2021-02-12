package com.dimalka.moviescraper.notificationservice.service;

import com.dimalka.moviescraper.notificationservice.model.MailRequest;
import com.dimalka.moviescraper.notificationservice.model.MailResponse;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;
@Service
public class JavaMailService{
@Autowired
private JavaMailSender javaMailSender;
@Autowired
private Configuration config;

//    public void sendEmail(String to, String body, String subject){
//        System.out.println("Sending...");
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo(to);
//        simpleMailMessage.setSubject(subject);
//        simpleMailMessage.setText(body);
//        javaMailSender.send(simpleMailMessage);
//        System.out.println("Sent!");
//    }

    public MailResponse sendEmail(MailRequest mailRequest, Map<String, Object> model){
        MailResponse response = new MailResponse();
        MimeMessage message = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name()
            );
//            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

            Template template = config.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            helper.setTo(mailRequest.getTo());
            helper.setText(html, true);
            helper.setSubject(mailRequest.getSubject());
            helper.setFrom(mailRequest.getFrom());
            javaMailSender.send(message);
            response.setMessage("Mail sent to : "+mailRequest.getTo());
            response.setStatus(Boolean.TRUE);
        } catch (MessagingException | IOException | TemplateException e) {
//            e.printStackTrace();
            response.setMessage("Mail to : "+mailRequest.getTo()+" failed!");
            response.setStatus(Boolean.FALSE);
        }



        return response;
    }
}
