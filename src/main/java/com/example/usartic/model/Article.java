package com.example.usartic.model;

import com.example.usartic.entity.ArticleEntity;
import com.example.usartic.entity.ColorArticleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Article {
    private Long id;
    private String text;
    private ColorArticleEnum color;

    public static Article articleToModel(ArticleEntity articleEntity) {
        Article model = new Article();
        model.id = articleEntity.getId();
        model.text = articleEntity.getText();
        model.color = articleEntity.getColor();
        return model;
    }
}
