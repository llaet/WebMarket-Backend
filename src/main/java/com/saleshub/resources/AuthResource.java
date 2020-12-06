package com.saleshub.resources;

import com.saleshub.config.security.JWTUtil;
import com.saleshub.config.security.UserSpringSecurity;
import com.saleshub.domain.dto.EmailDTO;
import com.saleshub.services.AuthService;
import com.saleshub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthService authService;

    @PostMapping("/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {

        UserSpringSecurity user = UserService.userAuthenticated();

        String token = this.jwtUtil.generateToken(user.getUsername());

        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot")
    public ResponseEntity<Void> createNewPassword(@Valid @RequestBody EmailDTO emailDTO) {

        this.authService.sendNewPassword(emailDTO.getEmail());

        return ResponseEntity.noContent().build();
    }
}
