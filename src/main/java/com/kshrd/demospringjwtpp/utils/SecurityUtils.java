package com.kshrd.demospringjwtpp.utils;

import com.kshrd.demospringjwtpp.model.entity.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static String getCurrentEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    public static AppUser getCurrentUser() {
        return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
