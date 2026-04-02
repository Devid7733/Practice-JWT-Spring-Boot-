package com.kshrd.demospringjwtpp.service;

import com.kshrd.demospringjwtpp.model.request.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    public void userRegister(RegisterRequest registerRequest);

}
