package org.example.cxc_hrm.controller;

import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cxc_hrm.domain.LoginDto;
import org.example.cxc_hrm.domain.UserCreateDto;
import org.example.cxc_hrm.domain.VerifyForgetPasswordDto;
import org.example.cxc_hrm.domain.response.StandardResponse;
import org.example.cxc_hrm.service.UserService;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/forgetPassword")
    public ResponseEntity<StandardResponse<?>> forgetPassword(@Valid @RequestParam String email) {
        return userService.forgetPassword(email);
    }
    @PutMapping("/verify-forget-password")
    public ResponseEntity<StandardResponse<?>> verifyForgetPassword(@Valid @RequestBody VerifyForgetPasswordDto verifyForgetPasswordDto) {
        return userService.verifyForgetCode(verifyForgetPasswordDto);

    }
    @PutMapping("/set-new-password")
    public ResponseEntity<StandardResponse<?>> setNewPassword(@RequestParam String email,@RequestParam String newPassword) {
        return userService.verifyForgetCodeSetNewPassword(email,newPassword);

    }
}
