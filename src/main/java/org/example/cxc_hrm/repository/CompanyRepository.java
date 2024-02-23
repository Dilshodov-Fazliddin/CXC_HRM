package org.example.cxc_hrm.repository;

import org.example.cxc_hrm.entity.CompanyEntity;
import org.example.cxc_hrm.entity.RoleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    List<CompanyEntity> searchAllBy(Pageable pageable);

}
