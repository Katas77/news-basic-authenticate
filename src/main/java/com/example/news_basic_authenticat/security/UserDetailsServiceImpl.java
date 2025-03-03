package com.example.news_basic_authenticat.security;

import com.example.news_basic_authenticat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app.security", name = "type", havingValue = "db")//Аннотация используется для условного создания bean-компонента Spring в зависимости от конфигурации свойства. В использовании, которое вы показали в вопросе, компонент будет создан только в том случае, если db свойство существует и имеет значение, отличное от false. Э
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userservice;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AppUserPrincipal(userservice.findByUserNickname(username));

    }
}
