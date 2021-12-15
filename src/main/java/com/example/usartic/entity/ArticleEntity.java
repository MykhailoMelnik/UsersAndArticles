package com.example.usartic.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "articles")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private ColorArticleEnum color;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Long getUser() {
        return user.getId();
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
