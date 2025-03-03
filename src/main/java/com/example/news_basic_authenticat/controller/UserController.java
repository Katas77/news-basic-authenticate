package com.example.news_basic_authenticat.controller;

import com.example.news_basic_authenticat.aop.AllowedUserDell;
import com.example.news_basic_authenticat.aop.AllowedUserFindById;
import com.example.news_basic_authenticat.aop.AllowedUserUpdate;
import com.example.news_basic_authenticat.model.user.RoleType;
import com.example.news_basic_authenticat.service.UserService;
import com.example.news_basic_authenticat.web.dto.user.CreateUserRequest;
import com.example.news_basic_authenticat.web.dto.user.UserListResponse;
import com.example.news_basic_authenticat.web.dto.user.UserResponse;
import com.example.news_basic_authenticat.web.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/admin/{pageNumber}/{pageSize}")
    public ResponseEntity<UserListResponse> findAll(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return ResponseEntity.ok(userMapper.userListResponseList(userService.findAll(pageNumber, pageSize)));
    }

    @AllowedUserFindById
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userMapper.userToResponse(userService.findById(id, userDetails)));
    }

    @PostMapping("/public")
    public ResponseEntity<String> create(@RequestBody @Valid CreateUserRequest request, @RequestParam RoleType roleType) {
        return userService.create(userMapper.requestToUser(request), roleType);
    }

    @AllowedUserUpdate
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long userId, @RequestBody CreateUserRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        return userService.update(userMapper.requestToUser(userId, request));
    }

    @AllowedUserDell
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return userService.deleteById(id);
    }
}
