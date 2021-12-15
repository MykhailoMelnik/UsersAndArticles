package com.example.usartic.service.impl;

import com.example.usartic.entity.ColorArticleEnum;
import com.example.usartic.entity.UserEntity;
import com.example.usartic.exeption.UserNotFoundException;
import com.example.usartic.exeption.UserAlreadyExistException;
import com.example.usartic.model.User;
import com.example.usartic.repository.UserRepository;
import com.example.usartic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Stream<User> getAllUsers() {
        List<UserEntity> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users.stream().map(User::userToModel);
    }

    @Override
    public User getUser(Long id) throws UserNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (!userEntity.isPresent()) {
            throw new UserNotFoundException("user do not exist");
        }
        return User.userToModel(userEntity.get());
    }

    @Override
    public Stream<User> getUsersOlder(int age) throws UserNotFoundException {
        List<UserEntity> users = new ArrayList<>(userRepository.findAllByAgeAfter(age));
        if (users.size() > 1) {
            return users.stream().map(User::userToModel);
        } else {
            throw new UserNotFoundException("user do not exist");
        }
    }

    @Override
    public List<UserEntity> getUsersByArticleColor(String color) throws UserNotFoundException {
        List<UserEntity> entityList = userRepository.findUserAndArticleByColor(color);
        if (entityList.isEmpty()) {
            throw new UserNotFoundException("user does not exist with this color");
        } else {
            entityList.forEach(userEntity -> userEntity.getArticles()
                    .removeIf(articleEntity -> !articleEntity.getColor().getColorValue().equals(color)));
            return entityList;
        }
    }

    @Override
    public List<UserEntity> getUserWithMoreArticles(int articlesMorThen) throws UserNotFoundException {
        userRepository.findAll();
        return userRepository.findAllByArticles(articlesMorThen);
    }

    @Override
    public UserEntity saveUser(UserEntity user) throws UserAlreadyExistException {
        if (userRepository.findByName(user.getName()) != null) {
            throw new UserAlreadyExistException("User is already exist in database");
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public String deleteUser(Long id) throws UserNotFoundException {
        if (!userRepository.findById(id).isPresent()) {
            throw new UserNotFoundException("user does not exist in database");
        } else {
            String name = userRepository.findById(id).get().getName();
            userRepository.deleteById(id);
            return name;
        }
    }
}