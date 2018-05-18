package com.duynguyen.personal.personalproject.service;

import com.duynguyen.personal.personalproject.domain.MyUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private MyUserService myUserService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUser myUser = myUserService.findByEmail(email);
        if (myUser == null) {
            throw new UsernameNotFoundException(email);
        }

        return new User(myUser.getEmail(), myUser.getPassword(), emptyList());
    }
}
