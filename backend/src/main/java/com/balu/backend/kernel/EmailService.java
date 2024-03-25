package com.balu.backend.kernel;

import com.balu.backend.kernel.templates.EmailTemplates;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailService {
    private JavaMailSender javaMailSender;
    public void sendEmailNewAccount (String email, String code) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setTo(email);
            helper.setSubject("Bienvenido a la plataforma");
            helper.setText(EmailTemplates.newAccountTemplate(code),true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
