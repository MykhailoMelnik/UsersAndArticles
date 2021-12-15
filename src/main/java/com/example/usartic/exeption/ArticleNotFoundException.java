package com.example.usartic.exeption;

public class ArticleNotFoundException extends Exception{
    public ArticleNotFoundException(String message) {
        super(message);
    }
}
