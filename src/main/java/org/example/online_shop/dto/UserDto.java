package org.example.online_shop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long userId;
    private String name;
    private String username;
    private String email;
//    private String password;
    private String phone;
    private String address;
    private boolean status;
    private int roleId;
}

