package com.etron.services;

import com.etron.models.AppUser;
import com.etron.repositories.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    public AppUserService(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    public UserDetails loadUserByUsername(String s) throws Exception{
        AppUser appUser = findByEmail(s);

        return User.builder()
                .username(appUser.getEmail())
                .password(appUser.getPassword())
                .roles(appUser.getRole().getAppRole().name())
                .build();

    }


    public AppUser findByEmail(String email) {
        return appUserRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("AppUser not Found with Email :" + email));
    }
}
