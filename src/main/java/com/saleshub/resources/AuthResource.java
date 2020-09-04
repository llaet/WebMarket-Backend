package com.saleshub.resources;

import com.saleshub.config.security.JWTUtil;
import com.saleshub.config.security.UserSpringSecurity;
import com.saleshub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {

        UserSpringSecurity user = UserService.userAuthenticated();

        String token = this.jwtUtil.generateToken(user.getUsername());

        response.addHeader("Authorization", "Bearer " + token);

        return ResponseEntity.noContent().build();
    }
}
