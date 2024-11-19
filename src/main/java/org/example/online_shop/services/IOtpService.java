package org.example.online_shop.services;

import org.example.online_shop.dto.OtpDto;
import org.example.online_shop.models.OtpModel;

public interface IOtpService extends IBaseService<OtpDto, OtpModel, Long> {
    OtpDto findByOtpCode(String otpCode);
}
