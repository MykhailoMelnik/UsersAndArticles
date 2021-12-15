package com.example.usartic.service;

import com.example.usartic.entity.ArticleEntity;
import com.example.usartic.exeption.ArticleNotFoundException;
import com.example.usartic.exeption.UserNotFoundException;


public interface ArticleService {

    Object createArticle(ArticleEntity article, Long userId) throws UserNotFoundException;

    Object deleteArticle(Long id) throws ArticleNotFoundException;
}
