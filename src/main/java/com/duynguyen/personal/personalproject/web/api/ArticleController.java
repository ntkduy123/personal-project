package com.duynguyen.personal.personalproject.web.api;

import com.duynguyen.personal.personalproject.domain.Article;
import com.duynguyen.personal.personalproject.service.ArticleService;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity updateArticle(@PathVariable Long id) {
        System.out.println(id);
        articleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateArticle(@RequestBody String data) {
        System.out.println(data);
        Article article = new Gson().fromJson(data, Article.class);

        article.setDate(new Date());

        articleService.save(article);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Article getArticleById(@PathVariable Long id) {
        return articleService.findById(id);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> getAllArticles() {
        return articleService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addArticle(@RequestBody String data) {
        Article article = new Gson().fromJson(data, Article.class);

        article.setDate(new Date());

        articleService.save(article);
        return ResponseEntity.ok().build();
    }
}
