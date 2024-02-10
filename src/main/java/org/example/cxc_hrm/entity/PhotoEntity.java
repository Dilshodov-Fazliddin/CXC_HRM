package org.example.cxc_hrm.entity;

import jakarta.persistence.Entity;
import lombok.*;


@Entity(name = "images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhotoEntity extends BaseEntity{
    private String email;
}
