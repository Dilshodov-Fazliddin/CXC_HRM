package org.example.cxc_hrm.controller;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.example.cxc_hrm.domain.LoginDto;
import org.example.cxc_hrm.domain.UserCreateDto;
import org.example.cxc_hrm.domain.response.JwtResponse;
import org.example.cxc_hrm.domain.response.StandardResponse;
import org.example.cxc_hrm.entity.UserEntity;
import org.example.cxc_hrm.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cxc/v1/user/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<StandardResponse<?>>signUp(@RequestBody UserCreateDto userCreateDto){
        return userService.signUp(userCreateDto);
    }
    @PostMapping("/login")
    public ResponseEntity<StandardResponse<Object>>login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }

    @PutMapping("/verify")
    public ResponseEntity<StandardResponse<?>> verify(@RequestParam int code, @RequestParam String email) {
        return userService.verify(code, email);
    }

}
