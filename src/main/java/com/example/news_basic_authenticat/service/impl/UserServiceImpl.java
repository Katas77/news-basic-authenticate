package com.example.news_basic_authenticat.service.impl;



import com.example.news_basic_authenticat.exception.EntityNotFoundException;
import com.example.news_basic_authenticat.model.user.Role;
import com.example.news_basic_authenticat.model.user.RoleType;
import com.example.news_basic_authenticat.model.user.User;
import com.example.news_basic_authenticat.repository.UserRepository;
import com.example.news_basic_authenticat.service.UserService;
import com.example.news_basic_authenticat.utils.BeanUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private boolean checkUser = false;


    @Override
    public List<User> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        log.debug("Fetching users with pagination parameters: {}", pageable);
        return userRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    @Override
    public User findById(Long id) {
        log.debug("Fetching user by ID {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("User with ID {0} not found", id)));
    }

    @Override
    @Transactional
    public User findById(Long id, UserDetails userDetails) {
        if (checkUser) {
            checkUser = false;
            id = userRepository.findByNickname(userDetails.getUsername()).orElseThrow().getId();
            return userRepository.findById(id).orElseThrow();
        } else {
            return this.findById(id);
        }
    }

    @Override
    public ResponseEntity<String> create(User user, RoleType roleType) {
        if (userRepository.existsByNickname(user.getNickname())) {
            return ResponseEntity.badRequest()
                    .body(MessageFormat.format("User with nickname {0} already exists", user.getNickname()));
        }
        Role role = Role.from(roleType);
        user.setRoles(Collections.singletonList(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        role.setUser(user);
        userRepository.saveAndFlush(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                MessageFormat.format("User with Nickname {0} created successfully", user.getNickname())
        );
    }

    @Override
    public ResponseEntity<String> update(User user) {
        Optional<User> existedUser = userRepository.findById(user.getId());
        if (existedUser.isPresent()) {
            BeanUtils.copyNonNullProperties(user, existedUser.get());
            userRepository.save(existedUser.get());
            return ResponseEntity.ok(
                    MessageFormat.format("User with ID {0} updated successfully", user.getId())
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public void deleteAllUsers() {
        log.warn("Deleting all users!");
        userRepository.deleteAll();
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteById(Long id) {
        log.info("Deleting user with ID {}", id);
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok(
                MessageFormat.format("User with ID {0} deleted successfully", id)
        );
    }

    @Override
    public User findByUserNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("Username not found!"));
    }


    @Override
    public void setCheck(Boolean aBoolean) {
        checkUser = aBoolean;
    }


}
