package com.duynguyen.personal.personalproject.service;

import com.duynguyen.personal.personalproject.domain.MyUser;

import javax.transaction.Transactional;

public interface MyUserService {
    @Transactional
    MyUser save(MyUser user);

    boolean isUserExist(String email);

    MyUser findByToken(String token);
}
