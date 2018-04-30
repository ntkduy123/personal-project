package com.duynguyen.personal.personalproject.service;

import com.duynguyen.personal.personalproject.domain.MyUser;
import com.duynguyen.personal.personalproject.domain.VerificationToken;
import com.duynguyen.personal.personalproject.repository.jpa.VerificationTokenRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Resource
    private VerificationTokenRepository tokenRepository;

    @Override
    public void create(MyUser user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        tokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
