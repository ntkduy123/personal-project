package com.duynguyen.personal.personalproject.repository.jpa;

import com.duynguyen.personal.personalproject.domain.Article;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long>, QueryDslPredicateExecutor<Article>
{
}
