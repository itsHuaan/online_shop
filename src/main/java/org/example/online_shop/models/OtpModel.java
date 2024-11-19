package org.example.online_shop.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtpModel {
    private Long id;
    private String otpCode;
    private String email;
    private Boolean status = true;
    private LocalDateTime createdDate = LocalDateTime.now();
}

