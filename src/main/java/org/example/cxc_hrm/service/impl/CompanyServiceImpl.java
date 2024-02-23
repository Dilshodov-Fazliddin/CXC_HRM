package org.example.cxc_hrm.service.impl;

import lombok.AllArgsConstructor;
import org.example.cxc_hrm.domain.CompanyDto;
import org.example.cxc_hrm.domain.RoleDto;
import org.example.cxc_hrm.domain.response.StandardResponse;
import org.example.cxc_hrm.entity.CompanyEntity;
import org.example.cxc_hrm.exception.DataNotFoundException;
import org.example.cxc_hrm.mapper.CompanyMapper;
import org.example.cxc_hrm.repository.CompanyRepository;
import org.example.cxc_hrm.service.CompanyService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public ResponseEntity<StandardResponse<Object>> createCompany(CompanyDto companyDto) {
            companyRepository.save(companyMapper.toEntity(companyDto));
            return ResponseEntity.ok(StandardResponse.builder().data(null).status(200).message("Company successfully saved").build());
    }

    @Override
    public ResponseEntity<StandardResponse<?>> deleteCompany(UUID id) {
        companyRepository.deleteById(id);
        return ResponseEntity.ok(StandardResponse.builder().data(null).status(200).message("Company successfully deleted").build());
    }

    @Override
    public ResponseEntity<StandardResponse<?>> updateCompany(UUID id, CompanyDto companyDto) {
        CompanyEntity company = companyRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Company not found"));
        company.setName(companyDto.getName());
        companyRepository.save(company);
        return ResponseEntity.ok(StandardResponse.builder().data(null).status(200).message("Company successfully updated").build());
    }

    @Override
    public ResponseEntity<StandardResponse<List<CompanyDto>>> getAllCompany(int size, int page) {
        Sort sort = Sort.by(Sort.Direction.ASC,"name");
        Pageable pageable= PageRequest.of(page,size,sort);
        List<CompanyEntity>all=companyRepository.searchAllBy(pageable);
        List<CompanyDto> companies = mapCompanies(all);
        return ResponseEntity.ok(StandardResponse.<List<CompanyDto>>builder().message("This is companies").status(200).data(companies).build());
    }

    @Override
    public List<CompanyDto> mapCompanies(List<CompanyEntity> forMapping) {
        List<CompanyDto>companyDto=new ArrayList<>();
        for (CompanyEntity company:forMapping){
            companyDto.add(CompanyDto.builder()
                            .name(company.getName())
                            .businessDirection(company.getBusinessDirection())
                            .description(company.getDescription())
                            .members(company.getMembers())
                    .build()
            );
        }
        return companyDto;
    }
}
