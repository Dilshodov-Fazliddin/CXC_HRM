package org.example.cxc_hrm.repository;

import org.example.cxc_hrm.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity>findByMail(String email);
    boolean existsByMail(String email);
    Optional<UserEntity>findByMailAndCode(String email,Integer code);
    Optional<UserEntity>findUserEntityByCompanyNullAndId(UUID id);

}
