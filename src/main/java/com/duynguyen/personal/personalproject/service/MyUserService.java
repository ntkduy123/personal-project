package com.duynguyen.personal.personalproject.service;

import com.duynguyen.personal.personalproject.domain.MyUser;

import javax.transaction.Transactional;
import java.util.List;

public interface MyUserService {
    @Transactional
    MyUser save(MyUser user);

    boolean isUserExist(String email);

    MyUser findByToken(String token);

    List<MyUser> findAll();

    MyUser findByEmail(String email);
}
