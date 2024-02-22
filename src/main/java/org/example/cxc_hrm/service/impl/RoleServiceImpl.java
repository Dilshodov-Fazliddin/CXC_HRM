package org.example.cxc_hrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.cxc_hrm.domain.RoleDto;
import org.example.cxc_hrm.domain.response.StandardResponse;
import org.example.cxc_hrm.entity.RoleEntity;
import org.example.cxc_hrm.exception.DataNotFoundException;
import org.example.cxc_hrm.exception.NotAcceptableException;
import org.example.cxc_hrm.mapper.RoleMapper;
import org.example.cxc_hrm.repository.RoleRepository;
import org.example.cxc_hrm.service.RoleService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    @Override
    public ResponseEntity<StandardResponse<RoleEntity>> createRole(RoleDto roleDto) {
        roleRepository.save(roleMapper.toEntity(roleDto));
        return ResponseEntity.ok(StandardResponse.<RoleEntity>builder().data(null).message("Role successfully created").status(200).build());
    }

    @Override
    public ResponseEntity<StandardResponse<?>> deleteRole(UUID id) {
        roleRepository.deleteById(id);
        return ResponseEntity.ok(StandardResponse.builder().message("role deleted").status(200).data(null).build());
    }

    @Override
    public ResponseEntity<StandardResponse<RoleEntity>> updateRole(RoleDto roleDto, UUID id) {
        RoleEntity role=roleRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Role not found"));
        if (!roleDto.getName().isBlank()) {
            role.setName("ROLE_" + roleDto.getName().toUpperCase());
            roleRepository.save(role);
            return ResponseEntity.ok(StandardResponse.<RoleEntity>builder().data(null).status(200).message("role updated").build());
        }
        throw new NotAcceptableException("Something went wrong, please try again");
    }

    @Override
    public ResponseEntity<StandardResponse<List<RoleDto>>> getRoles(int size, int page) {
        Sort sort = Sort.by(Sort.Direction.ASC,"name");
        Pageable pageable= PageRequest.of(page,size,sort);
        List<RoleEntity>all=roleRepository.searchAllBy(pageable);
        List<RoleDto> roleForFronts = mapRoles(all);
        return ResponseEntity.ok(StandardResponse.<List<RoleDto>>builder().data(roleForFronts).message("All roles").data(roleForFronts).build());

    }

    @Override
    public List<RoleDto> mapRoles(List<RoleEntity> forMapping) {
        List<RoleDto> roleForFronts = new ArrayList<>();
        for (RoleEntity role : forMapping) {
            roleForFronts.add(RoleDto.builder()
                    .name(role.getName())
                    .build());
        }
        return roleForFronts;
    }
}
