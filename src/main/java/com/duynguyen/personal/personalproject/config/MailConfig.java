package com.duynguyen.personal.personalproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    private String host = System.getenv("EMAIL_HOST");

    private int port = Integer.parseInt(System.getenv("EMAIL_PORT"));

    private String username = System.getenv("EMAIL_USERNAME");

    private String password = System.getenv("EMAIL_PASSWORD");

    private boolean auth = true;

    private boolean startLLS = true;

    private boolean startLLSRequired = true;

    @Bean
    public JavaMailSender javaMailService() {

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", auth);
        mailProperties.put("mail.smtp.starttls.enable", startLLS);
        mailProperties.put("mail.smtp.starttls.required", startLLSRequired);

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setJavaMailProperties(mailProperties);
        return javaMailSender;
    }

}
