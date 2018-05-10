package com.duynguyen.personal.personalproject.service;

import com.sendgrid.Email;
import com.sendgrid.Response;

public interface EmailService {
    Response send(Email to, String subject, String message);
}
