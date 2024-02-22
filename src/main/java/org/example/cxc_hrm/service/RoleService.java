package org.example.cxc_hrm.service;

import org.example.cxc_hrm.domain.RoleDto;
import org.example.cxc_hrm.domain.response.StandardResponse;
import org.example.cxc_hrm.entity.RoleEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface RoleService {
    ResponseEntity<StandardResponse<RoleEntity>>createRole(RoleDto roleDto);
    ResponseEntity<StandardResponse<?>>deleteRole(UUID id);
    ResponseEntity<StandardResponse<RoleEntity>>updateRole(RoleDto roleDto,UUID id);
    ResponseEntity<StandardResponse<List<RoleDto>>> getRoles(int size, int page);
    List<RoleDto> mapRoles(List<RoleEntity> forMapping);
}
