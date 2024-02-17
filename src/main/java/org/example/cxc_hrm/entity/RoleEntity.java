package org.example.cxc_hrm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
@EqualsAndHashCode(callSuper = true)

@Entity(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleEntity extends BaseEntity {
    @Column(unique = true)
    private String name;
}
