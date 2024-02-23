package org.example.cxc_hrm.service;

import org.example.cxc_hrm.domain.CompanyDto;
import org.example.cxc_hrm.domain.RoleDto;
import org.example.cxc_hrm.domain.response.StandardResponse;
import org.example.cxc_hrm.entity.CompanyEntity;
import org.example.cxc_hrm.entity.RoleEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CompanyService {
    ResponseEntity<StandardResponse<Object>>createCompany(CompanyDto companyDto);
    ResponseEntity<StandardResponse<?>>deleteCompany(UUID id);
    ResponseEntity<StandardResponse<?>>updateCompany(UUID id,CompanyDto companyDto);
    ResponseEntity<StandardResponse<List<CompanyDto>>>getAllCompany(int size,int page);
    List<CompanyDto> mapCompanies(List<CompanyEntity> forMapping);
}
