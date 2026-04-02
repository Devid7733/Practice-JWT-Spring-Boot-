package com.kshrd.demospringjwtpp.service;

import com.kshrd.demospringjwtpp.model.entity.AppUser;
import com.kshrd.demospringjwtpp.model.request.RegisterRequest;
import com.kshrd.demospringjwtpp.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService{

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser appUser = appUserRepository.findUserByEmail(email);

        if(appUser == null) {
            System.out.println("User not found");
        }
        List<String> roles = appUserRepository.getAllRolesByUserId(appUser.getUserId());
        appUser.setRoles(roles);
        return appUser;
    }


    @Override
    public void userRegister(RegisterRequest registerRequest) {
        AppUser appUser = new AppUser();
        AppUser oldUser = appUserRepository.findUserByEmail(registerRequest.getEmail());
        if (oldUser != null) {
            throw new RuntimeException("Email already exists");
        }
        appUser.setFullName(registerRequest.getFullName());
        appUser.setEmail(registerRequest.getEmail());
        appUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        Integer userId = appUserRepository.registerUser(appUser);
//        Integer roleId = appUserRepository.findRoleIdByRoleName("USER");
        appUserRepository.insertUserRole(userId, 1);
    }
}
