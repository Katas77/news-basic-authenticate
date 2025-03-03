package com.example.news_basic_authenticat.repository;

import com.example.news_basic_authenticat.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    Page<News> findAll(Pageable pageable);

    List<News> findAllByCategory(String category);

    List<News> findAllByUser_id(Long user_id);

}
