package com.example.news_basic_authenticat.web.mapper;

import com.example.news_basic_authenticat.model.News;
import com.example.news_basic_authenticat.web.dto.news.NewsListResponse;
import com.example.news_basic_authenticat.web.dto.news.NewsResponse;
import com.example.news_basic_authenticat.web.dto.news.NewsResponseFindById;
import com.example.news_basic_authenticat.web.dto.news.createNewsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsMapper {
    News requestToNews(createNewsRequest request, UserDetails userDetails);

    @Mapping(source = "newsId", target = "id")
    News requestNews(Long newsId, createNewsRequest request, UserDetails userDetails);

    NewsResponse newsToResponse(News news);

    NewsResponseFindById newsToResponseAllField(News news);

    default NewsListResponse newsListResponseList(List<News> newsList) {
        NewsListResponse response = new NewsListResponse();
        response.setNewsResponses(newsList.stream().map(this::newsToResponse).collect(Collectors.toList()));
        return response;
    }
}

