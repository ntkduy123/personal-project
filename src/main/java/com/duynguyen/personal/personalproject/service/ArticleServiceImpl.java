package com.duynguyen.personal.personalproject.service;

import com.duynguyen.personal.personalproject.domain.Article;
import com.duynguyen.personal.personalproject.repository.jpa.ArticleRepository;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleRepository articleRepository;

    @Override
    public List<Article> findAll() {
        return Lists.newArrayList(articleRepository.findAll());
    }

    @Override
    public Article findById(Long id) {
        return articleRepository.findOne(id);
    }

    @Override
    public void save(Article article) {
        System.out.println("AVCadsadsad");
        this.articleRepository.save(article);
    }

    @Override
    public void delete(Long id) { this.articleRepository.delete(id); }
}
