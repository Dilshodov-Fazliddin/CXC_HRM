package org.example.cxc_hrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.cxc_hrm.domain.LoginDto;
import org.example.cxc_hrm.domain.VerifyForgetPasswordDto;
import org.example.cxc_hrm.domain.response.JwtResponse;
import org.example.cxc_hrm.domain.response.StandardResponse;
import org.example.cxc_hrm.domain.UserCreateDto;
import org.example.cxc_hrm.entity.UserEntity;
import org.example.cxc_hrm.exception.DataNotFoundException;
import org.example.cxc_hrm.exception.NotAcceptableException;
import org.example.cxc_hrm.jwt.JwtTokenService;
import org.example.cxc_hrm.mapper.UserMapper;
import org.example.cxc_hrm.repository.UserRepository;
import org.example.cxc_hrm.service.MailService;
import org.example.cxc_hrm.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtTokenService jwtTokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByMail(username).orElseThrow(()->new DataNotFoundException("User not found"));
    }

    @Override
    public ResponseEntity<StandardResponse<?>> signUp(UserCreateDto userCreateDto) {
        if (userRepository.existsByMail(userCreateDto.getMail()))
            throw new NotAcceptableException("This is user already exist");
        int code=new Random().nextInt(1000,9000);
        mailService.sendVerifyCode(code, userCreateDto.getMail());
        userRepository.save(userMapper.toEntity(userCreateDto,code));
        return ResponseEntity.ok(StandardResponse.<UserEntity>builder().data(null).message("User successfully saved").status(200).build());
    }

    @Override
    public ResponseEntity<StandardResponse<Object>> login(LoginDto loginDto) {
        UserEntity user = userRepository.findByMail(loginDto.getMail()).orElseThrow(() -> new DataNotFoundException("User not found"));
        if (passwordEncoder.matches(loginDto.getPassword(),user.getPassword())){
            if (user.getIsEnabled()){
                return ResponseEntity.ok(StandardResponse.builder()
                        .data(JwtResponse.builder().accessToken(jwtTokenService.generateAccessToken(user)).build())
                        .message("Login in system")
                        .status(200)
                        .build());
            }
            throw new NotAcceptableException("Your account has blocked");
        }
        throw new NotAcceptableException("Password or Mail is not correct");
    }

    @Override
    public ResponseEntity<StandardResponse<?>> verify(Integer code, String email) {
        UserEntity user = userRepository.findByMailAndCode(email,code).orElseThrow(() -> new DataNotFoundException("User not found"));
        user.setCode(null);
        user.setIsEnabled(true);
        return ResponseEntity.ok(StandardResponse.builder().status(200).message("User verified").data(jwtTokenService.generateAccessToken(userRepository.save(user))).build());
    }

    @Override
    public ResponseEntity<StandardResponse<?>> forgetPassword(String email) {
        UserEntity user = userRepository.findByMail(email).orElseThrow(() -> new DataNotFoundException("User not found"));
        int code=new Random().nextInt(1000,9000);
        user.setCode(code);
        userRepository.save(user);
        mailService.sendVerifyCode(code,email);
        return ResponseEntity.ok(StandardResponse.builder().status(200).message("send verify code").data(null).build());
    }

    @Override
    public ResponseEntity<StandardResponse<?>> verifyForgetCode(VerifyForgetPasswordDto dto) {
        UserEntity user = userRepository.findByMailAndCode(dto.getEmail(), dto.getEmailCode()).orElseThrow(() -> new DataNotFoundException("User not found"));
        userRepository.save(user);
        return ResponseEntity.ok(StandardResponse.builder().data(null).message("code is checked").status(200).build());
    }

    @Override
    public ResponseEntity<StandardResponse<?>> verifyForgetCodeSetNewPassword(String email, String password) {
        UserEntity user = userRepository.findByMail(email).orElseThrow(() -> new DataNotFoundException("User not found"));
        if (passwordEncoder.matches(password, user.getPassword())){
            throw new NotAcceptableException("this password is the same as the old password");
        }else {
            user.setCode(null);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        }
        return ResponseEntity.ok(StandardResponse.builder().status(200).message("your password is changed").data(null).build());
    }

    @Override
    public ResponseEntity<StandardResponse<?>> blockUser(UUID id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found"));
        user.setIsEnabled(false);
        userRepository.save(user);
        return ResponseEntity.ok(StandardResponse.builder().data(null).status(200).message("User successfully blocked").build());
    }

    @Override
    public ResponseEntity<StandardResponse<?>> unblockUser(UUID id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found"));
        user.setIsEnabled(true);
        userRepository.save(user);
        return ResponseEntity.ok(StandardResponse.builder().message("User successfully unblocked").status(200).data(null).build());
    }


}
