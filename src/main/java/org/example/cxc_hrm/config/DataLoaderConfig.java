package org.example.cxc_hrm.config;

import lombok.RequiredArgsConstructor;
import org.example.cxc_hrm.entity.RoleEntity;
import org.example.cxc_hrm.entity.UserEntity;
import org.example.cxc_hrm.repository.RoleRepository;
import org.example.cxc_hrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDate;


@Configuration
@RequiredArgsConstructor
public class DataLoaderConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository userRoleRepository;
    @Value("${forDataLoader}")
    private String forDataLoader;

    @Override
    public void run(String... args) {
        if (forDataLoader.equals("always")) {

            RoleEntity roleAdmin = userRoleRepository.save(RoleEntity.builder().name("ROLE_ADMIN").build());
            RoleEntity roleUser = userRoleRepository.save(RoleEntity.builder().name("ROLE_USER").build());
            System.out.println("Basic Role successfully saved");
            userRepository.save(UserEntity.builder().firstname("admin").lastname("admin").birthDay(LocalDate.parse("2003-01-01")).mail("admin@gmail.com").isCredentialsNonExpired(true).isAccountNonExpired(true).isAccountNonLocked(true).isEnabled(true).password(passwordEncoder.encode("admin")).role(roleAdmin).build());
            System.out.println("Admin saved");
            userRepository.save(UserEntity.builder().firstname("user").lastname("user").birthDay(LocalDate.parse("2003-01-01")).mail("user@gmail.com").isCredentialsNonExpired(true).isAccountNonExpired(true).isAccountNonLocked(true).isEnabled(true).password(passwordEncoder.encode("user")).role(roleUser).build());
            System.out.println("User saved");
        }
    }
}
