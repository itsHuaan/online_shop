package org.example.online_shop.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {
    private Long id;
    private String type = "Bearer";
    private String token;
    private String username;
    private String email;
    private Boolean isActive;
    private String roleName;
}
