package com.saleshub.services;

import com.saleshub.domain.SaleOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    protected String emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired(required = false)
    private JavaMailSender javaMailSender;

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

    protected String getStringSaleOrderHtmlTemplate(SaleOrder saleOrder) {

        Context context = new Context();
        context.setVariable("saleOrder",saleOrder);

        return this.templateEngine.process("email/SaleOrderConfirmation",context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(SaleOrder saleOrder){
        try {
            MimeMessage mimeMessage = prepareMimeMessageFromSaleOrder(saleOrder);
            sendHtmlEmail(mimeMessage);
        } catch (MessagingException me) {
            sendOrderConfirmationEmail(saleOrder);
        }
    }

    protected MimeMessage prepareMimeMessageFromSaleOrder(SaleOrder saleOrder) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(saleOrder.getCustomer().getEmail());
        mimeMessageHelper.setFrom(emailSender);
        mimeMessageHelper.setSubject("Pedido confirmado! Código: " + saleOrder.getId());
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(getStringSaleOrderHtmlTemplate(saleOrder),true);

        return mimeMessage;
    }
}
