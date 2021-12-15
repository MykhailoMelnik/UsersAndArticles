package com.example.usartic.model;

import com.example.usartic.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private int age;

    public static User userToModel(UserEntity userEntity) {
        User model = new User();
        model.setId(userEntity.getId());
        model.setName(userEntity.getName());
        model.setAge(userEntity.getAge());
        return model;
    }
}

