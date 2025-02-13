package com.example.news_basic_authenticat.service;



import com.example.news_basic_authenticat.model.News;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface NewsService {
    List<News> findAll(int pageNumber, int pageSize);

    News findById(Long id);

    ResponseEntity<String> save(News news);

    ResponseEntity<String> update(News news);

    ResponseEntity<String> deleteById(Long id);

    List<News> filterByCategory(String Category);

    List<News> filterByUserId(UserDetails userDetails);

}
