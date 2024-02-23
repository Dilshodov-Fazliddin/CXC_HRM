package org.example.cxc_hrm.mapper;

import lombok.RequiredArgsConstructor;
import org.example.cxc_hrm.domain.CompanyDto;
import org.example.cxc_hrm.entity.CompanyEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyMapper {
    public CompanyEntity toEntity(CompanyDto companyDto){
        return CompanyEntity.builder()
                .name(companyDto.getName())
                .description(companyDto.getDescription())
                .members(companyDto.getMembers())
                .businessDirection(companyDto.getBusinessDirection())
                .build();
    }
}
