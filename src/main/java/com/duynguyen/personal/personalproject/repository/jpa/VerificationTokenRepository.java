package com.duynguyen.personal.personalproject.repository.jpa;

import com.duynguyen.personal.personalproject.domain.MyUser;
import com.duynguyen.personal.personalproject.domain.VerificationToken;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long>, QueryDslPredicateExecutor<VerificationToken>
{
    VerificationToken findByToken(String token);

    VerificationToken findByMyUser(MyUser user);
}
