package org.example.cxc_hrm.entity;

import jakarta.persistence.Entity;
import lombok.*;
@EqualsAndHashCode(callSuper = true)

@Entity(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleEntity extends BaseEntity {
    private String name;
}
