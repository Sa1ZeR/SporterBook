package me.sa1zer_.sporterbook.service;

public interface MailService {

    void sendMail(String mailTo, String subject, String message);
}
