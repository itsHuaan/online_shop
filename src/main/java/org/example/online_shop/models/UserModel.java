package org.example.online_shop.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {
    private String name;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Long roleId;
    private Boolean status = true;
    private LocalDateTime createdDate;
}

