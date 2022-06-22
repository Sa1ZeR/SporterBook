package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleMailService implements MailService {

    @Value("${spring.mail.username}")
    private String mailFrom;
    private final JavaMailSender mailSender;

    public SimpleMailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String mailTo, String subject, String message) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(mailTo);
        smm.setFrom(mailFrom);
        smm.setSubject(subject);
        smm.setText(message);

        mailSender.send(smm);
    }
}
