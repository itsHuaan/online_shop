package org.example.online_shop.services.impl;

import org.example.online_shop.dto.OtpDto;
import org.example.online_shop.entities.OtpEntity;
import org.example.online_shop.mappers.impl.OtpMapper;
import org.example.online_shop.models.OtpModel;
import org.example.online_shop.repositories.IOtpRepository;
import org.example.online_shop.services.IOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.example.online_shop.utils.Const.OTP_LENGTH;
import static org.example.online_shop.utils.Const.SALTCHARS;

@Service
public class OtpService implements IOtpService {
    private final IOtpRepository otpRepository;
    private final OtpMapper otpMapper;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    public OtpService(IOtpRepository otpRepository, OtpMapper otpMapper) {
        this.otpRepository = otpRepository;
        this.otpMapper = otpMapper;
        startOtpInvalidationTask();
    }

    @Override
    public OtpDto findByOtpCode(String otpCode) {
        return null;
    }

    @Override
    public List<OtpDto> findAll() {
        return List.of();
    }

    @Override
    public OtpDto findById(Long id) {
        return null;
    }

    @Override
    public OtpDto save(OtpModel otpModel) {
        OtpEntity otpEntity = otpMapper.toEntity(otpModel);
        otpEntity.setOtpCode(generateOtp());
        return otpMapper.toDTO(otpRepository.save(otpEntity));
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    private String generateOtp(){
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder otp = new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) {
            int index = secureRandom.nextInt(SALTCHARS.length());
            otp.append(SALTCHARS.charAt(index));
        }
        return otp.toString();
    }

    public void invalidateExpiredOtps(){
        LocalDateTime expireTime = LocalDateTime.now().minusMinutes(3);
        List<OtpEntity> expiredOtps = otpRepository.findAllByCreatedDateBeforeAndStatusTrue(expireTime);
        for (OtpEntity otpEntity : expiredOtps) {
            otpEntity.setStatus(false);
            otpRepository.save(otpEntity);
        }
    }
    private void startOtpInvalidationTask() {
        scheduler.scheduleWithFixedDelay(() -> {
            try {
                invalidateExpiredOtps();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
