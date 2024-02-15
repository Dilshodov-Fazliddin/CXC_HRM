package org.example.cxc_hrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.cxc_hrm.exception.DataNotFoundException;
import org.example.cxc_hrm.repository.UserRepository;
import org.example.cxc_hrm.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByMail(username).orElseThrow(()->new DataNotFoundException("User not found"));
    }
}
