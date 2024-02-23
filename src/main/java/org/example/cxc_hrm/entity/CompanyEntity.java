package org.example.cxc_hrm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "company")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyEntity extends BaseEntity {
    @Column(unique = true)
    private String name;
    @Column(nullable = false)
    private String businessDirection;
    @Column(nullable = false)
    private Integer members;
    @Column(nullable = false)
    private String description;
}
