package org.example.online_shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.online_shop.configurations.UserDetailsImpl;
import org.example.online_shop.configurations.jwtConfig.JwtProvider;
import org.example.online_shop.models.SignInRequest;
import org.example.online_shop.models.SignInResponse;
import org.example.online_shop.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth")
@RestController
@RequestMapping(value = Const.API_PREFIX + "/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Operation(summary = "Sign Users In", tags = {"Auth"})
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest credentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtProvider.generateTokenByUsername(userDetails.getUsername());
        return new ResponseEntity<>(new SignInResponse(
                userDetails.getUserEntity().getUserId(),
                "Bearer",
                jwt,
                userDetails.getUsername(),
                userDetails.getUser().getEmail(),
                userDetails.getUser().getStatus(),
                userDetails.getRoleName()
        ), HttpStatus.OK);
    }
}
