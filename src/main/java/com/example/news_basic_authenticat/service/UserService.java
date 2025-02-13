package com.example.news_basic_authenticat.service;


import com.example.news_basic_authenticat.model.user.RoleType;
import com.example.news_basic_authenticat.model.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface UserService {

    List<User> findAll(int pageNumber, int pageSize);
    User findById(Long id, UserDetails userDetails);
    User findById(Long id);
    ResponseEntity<String> create(User user, RoleType roleType);
    ResponseEntity<String> update(User user);
    ResponseEntity<String> deleteById(Long id);
    User findByUserNickname(String nickname);
    void setCheck(Boolean aBoolean);
    void deleteAllUsers();
}
