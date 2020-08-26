package com.saleshub.services;

import com.saleshub.domain.SaleOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    protected String emailSender;

    @Override
    public void sendOrderConfirmationEmail(SaleOrder saleOrder) {
        SimpleMailMessage smm = prepareSimpleMailMessageFromSaleOrder(saleOrder);
        sendEmail(smm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromSaleOrder(SaleOrder saleOrder){

        SimpleMailMessage smm = new SimpleMailMessage();

        smm.setTo(saleOrder.getCustomer().getEmail());
        smm.setFrom(emailSender);
        smm.setSubject("Pedido confirmado! Código: " + saleOrder.getId());
        smm.setSentDate(new Date(System.currentTimeMillis()));
        smm.setText(saleOrder.toString());

        return smm;
    }
}
