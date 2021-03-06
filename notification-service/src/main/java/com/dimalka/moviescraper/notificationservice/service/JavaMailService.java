package com.dimalka.moviescraper.notificationservice.service;

import com.dimalka.moviescrapercommons.model.notificationservice.MailRequest;
import com.dimalka.moviescrapercommons.model.notificationservice.MailResponse;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger log = LoggerFactory.getLogger(this.getClass());

    public MailResponse sendEmail(MailRequest mailRequest, Map<String, Object> model){
        MailResponse response = new MailResponse();
        MimeMessage message = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name()
            );
            Template template = config.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setTo(mailRequest.getTo());
            helper.setText(html, true);
            helper.setSubject(mailRequest.getSubject());
            helper.setFrom(mailRequest.getFrom());
            javaMailSender.send(message);
            log.info("Mail sent to : "+mailRequest.getTo());
            response.setMessage("Mail sent to : "+mailRequest.getTo());
            response.setStatus(Boolean.TRUE);
        } catch (MessagingException | IOException | TemplateException e) {
            log.error(e.getLocalizedMessage());
            response.setMessage("Mail to : "+mailRequest.getTo()+" failed!");
            response.setStatus(Boolean.FALSE);
        }
        return response;
    }
}
