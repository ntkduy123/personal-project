package com.duynguyen.personal.personalproject.service;

import com.duynguyen.personal.personalproject.domain.MyUser;
import com.duynguyen.personal.personalproject.domain.VerificationToken;

public interface VerificationTokenService {

    void create(MyUser user, String token);

    public VerificationToken findByToken(String token);
}
