package org.example.cxc_hrm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyDto {
    private String name;
    private String businessDirection;
    private Integer members;
    private String description;
}
