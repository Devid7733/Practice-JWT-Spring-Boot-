package com.kshrd.demospringjwtpp.controller;

import com.kshrd.demospringjwtpp.jwt.JwtUtils;
import com.kshrd.demospringjwtpp.model.request.LoginRequest;
import com.kshrd.demospringjwtpp.model.request.RegisterRequest;
import com.kshrd.demospringjwtpp.model.response.ApiResponse;
import com.kshrd.demospringjwtpp.service.AppUserService;
import com.kshrd.demospringjwtpp.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;
    private final AppUserService appUserService;

    @PostMapping
    public ApiResponse<String> login(@RequestBody LoginRequest request) {

       try {
           authManager.authenticate(new UsernamePasswordAuthenticationToken(
                   request.getEmail(),
                   request.getPassword())
           );

           String token = jwtUtils.generateToken(request.getEmail());

           return ApiResponse.<String>builder()
                   .message("login successfully")
                   .status(HttpStatus.OK)
                   .timestamp(Instant.now())
                   .payload(token)
                   .build();

       } catch (Exception e) {
           System.out.println(e);
       }

        return null;
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/me")
    public String updateMe() {
        System.out.println(SecurityUtils.getCurrentEmail());
        return SecurityUtils.getCurrentEmail();
    }

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody RegisterRequest request) {
        appUserService.userRegister(request);
        return ApiResponse.<String>builder()
                .message("registered successfully")
                .status(HttpStatus.OK)
                .timestamp(Instant.now())
                .payload(null)
                .build();
    }

}
