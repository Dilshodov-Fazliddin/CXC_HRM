package org.example.cxc_hrm.entity;

import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "company")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyEntity extends BaseEntity {
    private String name;
    private String businessDirection;
    private Integer members;
    private String description;
}
