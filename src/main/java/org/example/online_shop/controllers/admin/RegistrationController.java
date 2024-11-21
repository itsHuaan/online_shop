package org.example.online_shop.controllers.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.online_shop.configurations.jwtConfig.JwtProvider;
import org.example.online_shop.models.EmailModel;
import org.example.online_shop.models.ForgetPasswordRequest;
import org.example.online_shop.models.OtpModel;
import org.example.online_shop.services.IEmailService;
import org.example.online_shop.services.IOtpService;
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
    private final IOtpService otpService;
    private final IEmailService emailService;
    private final JwtProvider jwtProvider;

    @Autowired
    public RegistrationController(IOtpService otpService, IEmailService emailService, JwtProvider jwtProvider) {
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
        otp.setOtpCode(otpService.generateOtp());
        return otpService.save(otp) == 1
                ? new ResponseEntity<>(otp.getOtpCode(), HttpStatus.CREATED)
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


    @Operation(summary = "get otp and send mail", tags = {"02. Registration"})
    @GetMapping("get-otp-send-email")
    public ResponseEntity<?> getOtpAndSendMail(@RequestParam String email){
        OtpModel otp = new OtpModel();
        otp.setEmail(email);
        otp.setOtpCode(otpService.generateOtp());
        EmailModel email1 = new EmailModel();
        email1.setRecipient(email);
        email1.setSubject("OTP");
        email1.setBody(otp.getOtpCode());
        emailService.send(email1);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
