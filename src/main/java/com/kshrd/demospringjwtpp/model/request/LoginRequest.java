package com.kshrd.demospringjwtpp.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Schema(defaultValue = "meng@gmail.com")
    private String email;

    @Schema(defaultValue = "123")
    private String password;
}
