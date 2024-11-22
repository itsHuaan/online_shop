package org.example.online_shop.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpUserRequest {
    private String name;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String code;
}
