package com.example.usartic.repository;

import com.example.usartic.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByName(String userName);

    List<UserEntity> findAllByAgeAfter(int age);

    @Query(value =
            "select users.name, count(*) from users inner join articles a on users.id = a.user_id group by users.name having count(user_id) > :value"
            , nativeQuery = true)
    List<UserEntity> findAllByArticles(@Param("value") int value);


    @Query(value =
            "SELECT * FROM users AS u INNER JOIN articles AS a WHERE u.id = a.user_id AND a.color = :color"
            , nativeQuery = true)
    List<UserEntity> findUserAndArticleByColor(@Param("color") String color);
}
