package org.example.cxc_hrm.entity;

import jakarta.persistence.Entity;
import lombok.*;
@EqualsAndHashCode(callSuper = true)
@Entity(name = "images")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageEntity extends BaseEntity{
    private String url;
}
