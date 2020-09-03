package com.saleshub.services;

import com.saleshub.config.security.UserSpringSecurity;
import com.saleshub.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerService customerService;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Customer customer = this.customerService.findByEmail(email);

        if(customer == null){
            throw new UsernameNotFoundException(email);
        }
        return new UserSpringSecurity(
                customer.getId(),customer.getEmail(),customer.getPassword(),customer.getCustomerProfiles());
    }
}
