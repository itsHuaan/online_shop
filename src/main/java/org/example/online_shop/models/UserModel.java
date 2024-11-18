package org.example.online_shop.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String address;
    private int roleId = 2;
    private Boolean status = true;
    private String profilePicture;
    private LocalDateTime createdDate = LocalDateTime.now();
}

