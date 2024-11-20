package org.example.online_shop.controllers.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.online_shop.configurations.jwtConfig.JwtProvider;
import org.example.online_shop.dto.OtpDto;
import org.example.online_shop.mappers.impl.OtpMapper;
import org.example.online_shop.models.EmailModel;
import org.example.online_shop.models.ForgetPasswordRequest;
import org.example.online_shop.models.OtpModel;
import org.example.online_shop.services.impl.EmailService;
import org.example.online_shop.services.impl.OtpService;
import org.example.online_shop.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "02. Registration")
@RestController
@RequestMapping(value = Const.API_PREFIX + "/registration")
public class RegistrationController {
    private final OtpService otpService;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;

    @Autowired
    public RegistrationController(OtpService otpService, EmailService emailService, JwtProvider jwtProvider) {
        this.otpService = otpService;
        this.emailService = emailService;
        this.jwtProvider = jwtProvider;
    }


    @Operation(summary = "Sending email", tags = {"02. Registration"})
    @PostMapping("send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailModel emailModel) {
        return emailService.send(emailModel)
                ? new ResponseEntity<>("Email sent", HttpStatus.CREATED)
                : new ResponseEntity<>("Email not sent", HttpStatus.BAD_REQUEST);
    }


    @Operation(summary = "Generate OTP", tags = {"02. Registration"})
    @GetMapping("create-otp")
    public ResponseEntity<?> createOtp(@RequestParam String email) {
        OtpModel otp = new OtpModel();
        otp.setEmail(email);
        return otpService.save(otp) == 1
                ? new ResponseEntity<>("OTP Generated", HttpStatus.CREATED)
                : new ResponseEntity<>("Failed To Generate OTP", HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Forget Password", tags = {"02. Registration"})
    @PostMapping("forget-password")
    public ResponseEntity<?> forgetPassword(@RequestBody ForgetPasswordRequest credential) {
        String jwt = jwtProvider.generateForgetPasswordToken(credential.getEmail(), credential.getUsername());
        return jwt != null
                ? new ResponseEntity<>(jwt, HttpStatus.CREATED)
                : new ResponseEntity<>("Failed to generate token", HttpStatus.BAD_REQUEST);
    }
}
