package com.example.news_basic_authenticat.web.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @NotBlank(message = "nickname пользователя  должно быть заполнено!")
    @Size(min = 3, max = 30, message = "nickname пользователя е может быть меньше {min} и больше {max}!")
    private String nickname;
    @NotBlank(message = "password пользователя  должно быть заполнено!")
    private String password;
}
