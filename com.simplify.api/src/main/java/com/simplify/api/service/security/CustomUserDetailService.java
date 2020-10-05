package com.simplify.api.service.security;

import com.simplify.api.advice.exception.CUserNotFoundException;
import com.simplify.api.repo.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //return null;
        return userJpaRepository.findById(Long.valueOf(username)).orElseThrow(CUserNotFoundException::new);
    }
}
