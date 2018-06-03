package com.duynguyen.personal.personalproject.service;

import com.duynguyen.personal.personalproject.util.Trie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AutocompleteServiceImpl implements AutocompleteService {

    @Resource
    private ArticleService articleService;

    static {
        System.out.println("again ????");
        String[] arr = { "ts", "tsa", "tsb", "tsc" };
        for (String s : arr) {
            TRIE.insert(s);
        }
    }

}
