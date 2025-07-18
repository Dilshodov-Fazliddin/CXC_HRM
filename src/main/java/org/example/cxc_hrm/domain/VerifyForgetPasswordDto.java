package org.example.cxc_hrm.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyForgetPasswordDto {
    @NotNull(message = "Insert your code")
    private Integer emailCode;
    @NotNull(message = "Insert your email")
    private String email;
}
