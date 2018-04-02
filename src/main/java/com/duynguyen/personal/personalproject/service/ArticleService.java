package com.duynguyen.personal.personalproject.service;

import com.duynguyen.personal.personalproject.domain.Article;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();

    void save(Article article);
}
