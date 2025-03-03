package com.example.news_basic_authenticat.web.mapper;

import com.example.news_basic_authenticat.model.user.User;
import com.example.news_basic_authenticat.web.dto.user.CreateUserRequest;
import com.example.news_basic_authenticat.web.dto.user.UserListResponse;
import com.example.news_basic_authenticat.web.dto.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {NewsMapper.class})
public interface UserMapper {
    User requestToUser(CreateUserRequest request);

    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, CreateUserRequest request);

    UserResponse userToResponse(User user);

    default UserListResponse userListResponseList(List<User> users) {
        UserListResponse response = new UserListResponse();
        response.setUserResponses(users.stream().map(this::userToResponse).collect(Collectors.toList()));
        return response;
    }

}
