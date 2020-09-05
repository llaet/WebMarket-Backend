package com.saleshub.services;

import com.saleshub.domain.Customer;
import com.saleshub.domain.SaleOrder;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(SaleOrder saleOrder);
    void sendOrderConfirmationHtmlEmail(SaleOrder saleOrder);
    void sendEmail(SimpleMailMessage emailMessage);
    void sendHtmlEmail(MimeMessage msg);
    void sendNewPasswordEmail(Customer customer, String newPassword);
}
