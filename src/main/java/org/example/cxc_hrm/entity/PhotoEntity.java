package org.example.cxc_hrm.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "photos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhotoEntity extends BaseEntity{
    private String email;
}
