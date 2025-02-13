package com.example.news_basic_authenticat.web.dto.user;


import com.example.news_basic_authenticat.web.dto.comment.CommentResponse;
import com.example.news_basic_authenticat.web.dto.news.NewsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String nickname;
    private List<NewsResponse> newsResponses = new ArrayList<>();
    private List<CommentResponse> commentResponses = new ArrayList<>();
}
