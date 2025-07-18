package org.example.cxc_hrm.repository;

import org.example.cxc_hrm.entity.RoleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity>findByName(String name);
    List<RoleEntity> searchAllBy(Pageable pageable);
}
