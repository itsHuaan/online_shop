package org.example.online_shop.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {
    private Long userId;
    private String name;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String address;
    @Schema(title = "roleId", example = "2")
    private int roleId = 2;
    private Boolean status = true;
    private String profilePicture;
    private LocalDateTime createdDate = LocalDateTime.now();
}

