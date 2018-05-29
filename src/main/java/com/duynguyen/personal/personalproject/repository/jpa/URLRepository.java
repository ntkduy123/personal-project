package com.duynguyen.personal.personalproject.repository.jpa;

import com.duynguyen.personal.personalproject.domain.URL;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface URLRepository extends CrudRepository<URL, Long>, QueryDslPredicateExecutor<URL>
{
}
