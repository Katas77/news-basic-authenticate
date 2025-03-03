package com.example.news_basic_authenticat.repository;

import com.example.news_basic_authenticat.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {


}
