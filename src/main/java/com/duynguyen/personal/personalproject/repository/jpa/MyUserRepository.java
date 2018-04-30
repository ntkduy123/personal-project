package com.duynguyen.personal.personalproject.repository.jpa;

import com.duynguyen.personal.personalproject.domain.MyUser;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MyUserRepository extends CrudRepository<MyUser, Long>, QueryDslPredicateExecutor<MyUser>
{
    List<MyUser> findByEmail(String email);
}
