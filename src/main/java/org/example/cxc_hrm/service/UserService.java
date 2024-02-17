package org.example.cxc_hrm.service;

import org.example.cxc_hrm.domain.LoginDto;
import org.example.cxc_hrm.domain.response.StandardResponse;
import org.example.cxc_hrm.domain.UserCreateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
     ResponseEntity<StandardResponse<?>>signUp(UserCreateDto userCreateDto);
     ResponseEntity<StandardResponse<Object>>login(LoginDto loginDto);
     ResponseEntity<StandardResponse<?>> verify(Integer code, String email);


}
