package com.example.news_basic_authenticat.service.impl;



import com.example.news_basic_authenticat.model.News;
import com.example.news_basic_authenticat.repository.NewsRepository;
import com.example.news_basic_authenticat.repository.UserRepository;
import com.example.news_basic_authenticat.service.NewsService;
import com.example.news_basic_authenticat.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;


    @Override
    public List<News> findAll(int pageNumber, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt"); // Сортировка по полю "createdAt" по убыванию
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        log.debug("Fetching news with pagination parameters: {}", pageable);
        return newsRepository.findAll(pageable).getContent();
    }

    @Override
    public News findById(Long id) {
        log.debug("Fetching news by ID {}", id);
        return newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("News with ID {0} not found", id)));
    }

    @Override
    public ResponseEntity<String> save(News news) {
        log.info("Saving news with text {}", news.getText());
        newsRepository.save(news);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                MessageFormat.format("News with Text - {0} saved successfully", news.getText())
        );
    }


    @Transactional
    @Override
    public ResponseEntity<String> update(News news) {
        Optional<News> existedNews = newsRepository.findById(news.getId());
        if (existedNews.isPresent()) {
            BeanUtils.copyNonNullProperties(news, existedNews.get());
            newsRepository.save(existedNews.get());
            return ResponseEntity.ok(
                    MessageFormat.format("News with ID {0} updated successfully", news.getId())
            );
        } else {
            return ResponseEntity.notFound().build(); // Возвращаем 404 Not Found
        }
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteById(Long id) {
        log.info("Deleting news with ID {}", id);
        if (!newsRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Возвращаем 404 Not Found
        }
        newsRepository.deleteById(id);
        return ResponseEntity.ok(
                MessageFormat.format("News with ID {0} deleted successfully", id)
        );
    }
    @Override
    public List<News> filterByCategory(String category) {
        log.debug("Filtering news by category {}", category);
        return newsRepository.findAllByCategory(category);
    }

    @Transactional
    @Override
    public List<News> filterByUserId(UserDetails userDetails) {
        long userId = userRepository.findByNickname(userDetails.getUsername()).orElseThrow().getId();
        log.debug("Filtering news by user ID {}", userId);
        return newsRepository.findAllByUser_id(userId);
    }


}
