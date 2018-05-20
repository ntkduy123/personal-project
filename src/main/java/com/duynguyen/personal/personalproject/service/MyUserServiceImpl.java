package com.duynguyen.personal.personalproject.service;

import com.duynguyen.personal.personalproject.domain.MyUser;
import com.duynguyen.personal.personalproject.domain.VerificationToken;
import com.duynguyen.personal.personalproject.repository.jpa.MyUserRepository;
import com.duynguyen.personal.personalproject.repository.jpa.VerificationTokenRepository;
import com.google.common.collect.Lists;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class MyUserServiceImpl implements MyUserService {

    @Resource
    private MyUserRepository myUserRepository;

    @Resource
    private VerificationTokenRepository tokenRepository;

    @Transactional
    @Override
    public MyUser save(MyUser user) {
        return myUserRepository.save(user);
    }

    @Override
    public boolean isUserExist(String email) {
        List<MyUser> user = myUserRepository.findByEmail(email);
        return user.size() > 0;
    }

    @Override
    public MyUser findByToken(String token) {
        return tokenRepository.findByToken(token).getMyUser();
    }

    @Override
    public List<MyUser> findAll() {
        return Lists.newArrayList(myUserRepository.findAll());
    }

    @Override
    public MyUser findByEmail(String email) {
        return myUserRepository.findByEmail(email).get(0);
    }
}
