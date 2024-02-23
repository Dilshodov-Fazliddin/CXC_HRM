package org.example.cxc_hrm.controller;

import lombok.RequiredArgsConstructor;
import org.example.cxc_hrm.domain.response.StandardResponse;
import org.example.cxc_hrm.entity.enums.Position;
import org.example.cxc_hrm.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cxc/v1/user")
public class UserController {
    private final UserService userService;

    @PutMapping("/block")
    public ResponseEntity<StandardResponse<?>>blockUser(@RequestParam UUID id){
        return userService.blockUser(id);
    }

    @PutMapping("/unblock")
    public ResponseEntity<StandardResponse<?>>unblockUser(@RequestParam UUID id){
        return userService.unblockUser(id);
    }

    @PostMapping("/add-worker")
    public ResponseEntity<StandardResponse<?>>addWorker(@RequestParam UUID userId, @RequestParam UUID companyId, @RequestParam Position position){
        return userService.addWorker(userId,companyId,position);
    }

    @DeleteMapping("/delete-worker")
    public ResponseEntity<StandardResponse<?>>deleteWorker(@RequestParam UUID userId, @RequestParam UUID companyId){
        return userService.deleteWorker(userId,companyId);
    }
}
