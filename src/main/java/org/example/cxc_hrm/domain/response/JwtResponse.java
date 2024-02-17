package org.example.cxc_hrm.domain.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
}
