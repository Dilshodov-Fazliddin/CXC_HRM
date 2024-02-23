package org.example.cxc_hrm.service;

import org.example.cxc_hrm.domain.LoginDto;
import org.example.cxc_hrm.domain.VerifyForgetPasswordDto;
import org.example.cxc_hrm.domain.response.StandardResponse;
import org.example.cxc_hrm.domain.UserCreateDto;
import org.example.cxc_hrm.entity.enums.Position;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {
     ResponseEntity<StandardResponse<?>>signUp(UserCreateDto userCreateDto);
     ResponseEntity<StandardResponse<Object>>login(LoginDto loginDto);
     ResponseEntity<StandardResponse<?>> verify(Integer code, String email);
     ResponseEntity<StandardResponse<?>>forgetPassword(String email);
     ResponseEntity<StandardResponse<?>>verifyForgetCode(VerifyForgetPasswordDto dto);
     ResponseEntity<StandardResponse<?>>verifyForgetCodeSetNewPassword(String email,String password);
     ResponseEntity<StandardResponse<?>>blockUser(UUID id);
     ResponseEntity<StandardResponse<?>>unblockUser(UUID id);
     ResponseEntity<StandardResponse<?>>addWorker(UUID userId, UUID companyId, Position position);
     ResponseEntity<StandardResponse<?>>deleteWorker(UUID userId, UUID companyId);
}
