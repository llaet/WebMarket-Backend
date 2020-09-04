package com.saleshub.services;

import com.saleshub.config.security.UserSpringSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public static UserSpringSecurity userAuthenticated(){
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }


}
