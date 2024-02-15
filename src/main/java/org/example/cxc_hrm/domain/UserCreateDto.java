package org.example.cxc_hrm.domain;

import lombok.*;
import org.example.cxc_hrm.entity.CompanyEntity;
import org.example.cxc_hrm.entity.enums.Position;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateDto {
    private String firstname;
    private String lastname;
    private String mail;
    private String password;
    private LocalDate birthDay;
    private String username;
    private Position position;
    private CompanyEntity company;
}
