package com.example.news_basic_authenticat.service;

import com.example.news_basic_authenticat.model.Comment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {
    List<Comment> findAll(Long newsId);

    Comment findById(Long id);

    ResponseEntity<String> save(Comment comment);

    ResponseEntity<String> update(Comment comment);

    void dellAll();

    ResponseEntity<String> deleteById(Long id);

}
