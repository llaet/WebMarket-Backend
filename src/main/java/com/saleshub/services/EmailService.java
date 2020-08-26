package com.saleshub.services;

import com.saleshub.domain.SaleOrder;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(SaleOrder saleOrder);
    void sendEmail(SimpleMailMessage emailMessage);
}
