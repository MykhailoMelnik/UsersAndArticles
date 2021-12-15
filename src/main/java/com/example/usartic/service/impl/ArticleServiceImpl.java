package com.example.usartic.service.impl;

import com.example.usartic.entity.ArticleEntity;
import com.example.usartic.entity.UserEntity;
import com.example.usartic.exeption.ArticleNotFoundException;
import com.example.usartic.exeption.UserNotFoundException;
import com.example.usartic.model.Article;
import com.example.usartic.repository.ArticleRepository;
import com.example.usartic.repository.UserRepository;
import com.example.usartic.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    final
    ArticleRepository articleRepository;

    final
    UserRepository userRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }


    public Article createArticle(ArticleEntity article, Long userId) throws UserNotFoundException {
        if (userRepository.findById(userId).isPresent()) {
            UserEntity userEntity = userRepository.findById(userId).get();
            article.setUser(userEntity);
            articleRepository.save(article);
            return Article.articleToModel(article);
        } else {
            throw new UserNotFoundException("user do not exist");
        }
    }

    public String deleteArticle(Long articleId) throws ArticleNotFoundException {
        if (articleRepository.findById(articleId).isPresent()) {
            articleRepository.deleteById(articleId);
            return "article wos delete";
        } else {
            throw new ArticleNotFoundException("articles does not exist");
        }
    }
}