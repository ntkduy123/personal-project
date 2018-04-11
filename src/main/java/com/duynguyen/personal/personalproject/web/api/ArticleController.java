package com.duynguyen.personal.personalproject.web.api;

import com.duynguyen.personal.personalproject.domain.Article;
import com.duynguyen.personal.personalproject.service.ArticleService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/api/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Autowired
    ServletContext servletContext;

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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return System.getProperty("user.dir");
    }


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> getAllArticles() {
        return articleService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public HashMap addArticle(@RequestBody String data) {
        Article article = new Gson().fromJson(data, Article.class);

        article.setDate(new Date());

        articleService.save(article);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", article.getId().toString());
        return map;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile multipartFile, @RequestParam("fileName") String fileName) throws IOException, SQLException {
        File convFile  = new File("src/main/resources/static/images/" + fileName);
        FileOutputStream fos = new FileOutputStream(convFile);

        fos.write(multipartFile.getBytes());
        fos.close();
        return ResponseEntity.ok().build();
    }
}
