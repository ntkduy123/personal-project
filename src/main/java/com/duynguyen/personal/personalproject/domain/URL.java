package com.duynguyen.personal.personalproject.domain;

import javax.persistence.*;

@Entity
public class URL {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String originalURL;

    private String shortURL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }
}
