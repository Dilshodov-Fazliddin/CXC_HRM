package org.example.cxc_hrm.mapper;

import lombok.RequiredArgsConstructor;
import org.example.cxc_hrm.domain.UserCreateDto;
import org.example.cxc_hrm.entity.UserEntity;
import org.example.cxc_hrm.exception.DataNotFoundException;
import org.example.cxc_hrm.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class UserMapper {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity toEntity(UserCreateDto userCreateDto,Integer code){
        return UserEntity.builder()
                .firstname(userCreateDto.getFirstname())
                .lastname(userCreateDto.getLastname())
                .username(userCreateDto.getUsername())
                .birthDay(userCreateDto.getBirthDay())
                .role(roleRepository.findByName("ROLE_USER").orElseThrow(()->new DataNotFoundException("Role not found")))
                .mail(userCreateDto.getMail())
                .code(code)
                .notifications(null)
                .password(passwordEncoder.encode(userCreateDto.getPassword()))
                .company(null)
                .position(null)
                .isAccountNonExpired(true)
                .isEnabled(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .build();
    }
}
