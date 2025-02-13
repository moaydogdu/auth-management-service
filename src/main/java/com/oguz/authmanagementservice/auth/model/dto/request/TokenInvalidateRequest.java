package com.oguz.authmanagementservice.auth.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenInvalidateRequest {

    @NotBlank
    private String refreshToken;

}
