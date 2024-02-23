package org.example.cxc_hrm.controller;

import lombok.RequiredArgsConstructor;
import org.example.cxc_hrm.domain.CompanyDto;
import org.example.cxc_hrm.domain.response.StandardResponse;
import org.example.cxc_hrm.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cxc/v1/company")
public class CompanyController {
    private final CompanyService companyService;
    @PostMapping("/create")
    public ResponseEntity<StandardResponse<Object>> createCompany(@RequestBody CompanyDto companyDto){
        return companyService.createCompany(companyDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<StandardResponse<?>>deleteCompany(@RequestParam UUID id){
        return companyService.deleteCompany(id);
    }


    @PutMapping("/update")
    public ResponseEntity<StandardResponse<?>>updateCompany(@RequestParam UUID id,@RequestBody CompanyDto companyDto){
        return companyService.updateCompany(id,companyDto);
    }

    @GetMapping("/get")
    public ResponseEntity<StandardResponse<List<CompanyDto>>>getAllCompanies(@RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "0") int page){
        return companyService.getAllCompany(size,page);
    }
}
