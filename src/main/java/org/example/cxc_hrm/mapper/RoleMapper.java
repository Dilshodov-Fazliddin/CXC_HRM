package org.example.cxc_hrm.mapper;

import lombok.RequiredArgsConstructor;
import org.example.cxc_hrm.domain.RoleDto;
import org.example.cxc_hrm.entity.RoleEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleMapper {


    public RoleEntity toEntity(RoleDto roleDto){
        return RoleEntity.builder()
                .name("ROLE_"+roleDto.getName().toUpperCase())
                .build();
    }
}
