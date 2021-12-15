package com.example.usartic.service;

import com.example.usartic.entity.UserEntity;
import com.example.usartic.exeption.UserAlreadyExistException;
import com.example.usartic.exeption.UserNotFoundException;

import java.util.List;

public interface UserService {

    Object getAllUsers();

    Object getUser(Long id) throws UserNotFoundException;

    UserEntity saveUser(UserEntity user) throws UserAlreadyExistException;

    String deleteUser(Long id) throws UserNotFoundException;

    Object getUsersOlder(int id) throws UserNotFoundException;

    List<UserEntity> getUsersByArticleColor(String color) throws UserNotFoundException;

    List<UserEntity> getUserWithMoreArticles(int value) throws UserNotFoundException;
}
