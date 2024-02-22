package org.example.cxc_hrm.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cxc_hrm.domain.RoleDto;
import org.example.cxc_hrm.domain.response.StandardResponse;
import org.example.cxc_hrm.entity.RoleEntity;
import org.example.cxc_hrm.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/cxc/v1/role")
public class RoleController {
    private final RoleService roleService;
    @PostMapping("/create")
    public ResponseEntity<StandardResponse<RoleEntity>> save(@Valid @RequestBody RoleDto roleDto){
        return roleService.createRole(roleDto);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StandardResponse<?>>delete(@PathVariable UUID id){
        return roleService.deleteRole(id);
    }



    @GetMapping("/get-all")
    public ResponseEntity<StandardResponse<List<RoleDto>>>getAll(@RequestParam int size, @RequestParam int page){
        return roleService.getRoles(size,page);
    }


    @PutMapping("/update")
    public ResponseEntity<StandardResponse<RoleEntity>>update(@Valid @RequestBody RoleDto roleDto, @RequestParam UUID id){
        return roleService.updateRole(roleDto,id);
    }
}
