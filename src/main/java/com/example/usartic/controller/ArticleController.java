package com.example.usartic.controller;

import com.example.usartic.entity.ArticleEntity;
import com.example.usartic.exeption.ArticleNotFoundException;
import com.example.usartic.exeption.UserNotFoundException;
import com.example.usartic.service.ArticleService;
import com.example.usartic.service.impl.ArticleServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    final
    ArticleService articleService;

    public ArticleController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/save")
    public ResponseEntity createArticle(@RequestBody ArticleEntity article, @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(articleService.createArticle(article, userId));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity deleteArticle(Long id) {
        try {
            return ResponseEntity.ok(articleService.deleteArticle(id));
        } catch (ArticleNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
