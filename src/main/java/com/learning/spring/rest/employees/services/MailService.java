package com.learning.spring.rest.employees.services;

public interface MailService {
    void sendEmail(String to, String recipient, String emailAddress,String password);
}
