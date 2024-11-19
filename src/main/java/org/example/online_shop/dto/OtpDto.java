package org.example.online_shop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtpDto {
    private Long otpId;
    private String otpCode;
    private String email;
    private Boolean status;
    private LocalDateTime createdDate;
}
