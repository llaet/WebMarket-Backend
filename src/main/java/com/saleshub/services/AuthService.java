package com.saleshub.services;

import com.saleshub.config.security.SecurityConfig;
import com.saleshub.domain.Customer;
import com.saleshub.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmailService emailService;

    public void sendNewPassword(String email){

        Customer customer = this.customerService.findByEmail(email);

        if(customer == null){
            throw new ObjectNotFoundException("Email não encontrado");
        }

        String newPassword = createNewPassword();

        customer.setPassword(SecurityConfig.bCryptPasswordEncoder().encode(newPassword));

        this.customerService.save(customer);
        this.emailService.sendNewPasswordEmail(customer,newPassword);
    }

    private String createNewPassword() {

        StringBuilder stringBuilder = new StringBuilder();

        for(int index = 0; index < 10; index++){
            stringBuilder.append(getRandomChar());
        }

        return stringBuilder.toString();
    }

    private char getRandomChar(){
        Random random = new Random();

        int option = random.nextInt(3);

        switch (option) {
            case 0://generate a digit
                return (char) (random.nextInt(10) + 48);
            case 1:
                return (char) (random.nextInt(26) + 65);
            default:
                return (char) (random.nextInt(26) + 97);
        }
    }
}
