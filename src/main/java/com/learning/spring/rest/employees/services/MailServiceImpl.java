package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.utils.EmailConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private EmailConfiguration emailConfiguration;
    private JavaMailSenderImpl mailSender;

    @Autowired
    public MailServiceImpl(EmailConfiguration emailConfiguration) {
        this.emailConfiguration = emailConfiguration;
    }

    private JavaMailSenderImpl setEmailConfig() {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfiguration.getHost());
        mailSender.setPort(emailConfiguration.getPort());
        mailSender.setUsername(emailConfiguration.getUsername());
        mailSender.setPassword(emailConfiguration.getPassword());
        return mailSender;
    }

    @Override
    public void sendEmail(String to, String recipient,String emailAddress, String password) {
        setEmailConfig();
        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setFrom("admin@sv.ro");
        simpleMail.setTo(to);
        simpleMail.setSubject("Welcome to Employee Management App");
        simpleMail.setText("Hello "+recipient+",\n\n\t"+"Your account on EMA platform has been created. For login, please create your password by accessing the following link: http://localhost:8080/password?token="+ password +
                "\nRegards," +
                "\nAdministration");
        mailSender.send(simpleMail);
    }

}
