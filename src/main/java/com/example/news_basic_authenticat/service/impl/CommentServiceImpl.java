package com.example.news_basic_authenticat.service.impl;

import com.example.news_basic_authenticat.model.Comment;
import com.example.news_basic_authenticat.repository.CommentRepository;
import com.example.news_basic_authenticat.service.CommentService;
import com.example.news_basic_authenticat.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final NewsServiceImpl newsService;


    @Override
    public List<Comment> findAll(Long newsId) {
        log.debug("Fetching comments for news with ID {}", newsId);
        return newsService.findById(newsId).getCommentList();
    }

    @Override
    public Comment findById(Long id) {
        log.debug("Fetching comment by ID {}", id);
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Comment with ID {0} not found", id)));
    }

    @Override
    public ResponseEntity<String> save(Comment comment) {
        log.info("Saving comment with text {}", comment.getText());
        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                MessageFormat.format("Comment with Text - {0} saved successfully", comment.getText())
        );
    }

    @Transactional
    @Override
    public ResponseEntity<String> update(Comment comment) {
        Optional<Comment> existedComment = commentRepository.findById(comment.getId());
        if (existedComment.isPresent()) {
            BeanUtils.copyNonNullProperties(comment, existedComment.get());
            save(existedComment.get());
            return ResponseEntity.ok(
                    MessageFormat.format("Comment with ID {0} updated successfully", comment.getId())
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public void dellAll() {
        log.warn("Deleting all comments!");
        commentRepository.deleteAll();
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteById(Long id) {
        log.info("Deleting comment with ID {}", id);
        if (!commentRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Возвращаем 404 Not Found
        }
        commentRepository.deleteById(id);
        return ResponseEntity.ok(
                MessageFormat.format("Comment with ID {0} deleted successfully", id)
        );
    }


}
