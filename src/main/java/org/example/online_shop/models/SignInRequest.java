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
public class SignInRequest {
    @Schema(title = "username", example = "admin")
    @NotBlank
    private String username;
    @Schema(title = "password", example = "admin")
    @NotBlank
    private String password;
}
