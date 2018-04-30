//package com.duynguyen.personal.personalproject.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//@Configuration
//public class MailConfig {
//
//    private String username = System.getenv("SENDGRID_USERNAME");
//
//    private String password = System.getenv("SENDGRID_PASSWORD");
//
//    @Bean
//    public JavaMailSender javaMailService() {
//
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
////        javaMailSender.setHost(host);
////        javaMailSender.setPort(port);
//        javaMailSender.setUsername(username);
//        javaMailSender.setPassword(password);
//        return javaMailSender;
//    }
//
//}
