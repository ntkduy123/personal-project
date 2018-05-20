package com.duynguyen.personal.personalproject.repository.jpa;

import com.duynguyen.personal.personalproject.domain.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long>, QueryDslPredicateExecutor<Article>
{
    @Query("select a.title from Article a")
    List<String> findAllTitles();
}
