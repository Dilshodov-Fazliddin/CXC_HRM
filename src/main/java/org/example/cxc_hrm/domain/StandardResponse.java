package org.example.cxc_hrm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponse<T> {
    private String message;
    private Object data;
    private Integer status;

}
