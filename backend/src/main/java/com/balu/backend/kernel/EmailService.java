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
            helper.setSubject("Activar cuenta");
            helper.setText(EmailTemplates.newAccountTemplate(code),true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAdoptionApprovalTemplate(String email, String namePet){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setTo(email);
            helper.setSubject("¡Felicidades! se acepto tu solicitud de "+namePet);
            helper.setText(EmailTemplates.adoptionApprovalTemplate(namePet),true);
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void finalizeAdoptionTemplate(String email, String namePet){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setTo(email);
            helper.setSubject("¡Malas noticias! se finalizo"+namePet);
            helper.setText(EmailTemplates.finalizeAdoptionTemplate(namePet),true);
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void activeRequestTemplate(String email,String namePet,int countRequest){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setTo(email);
            helper.setSubject("¡Ven a revisar! tienes solicitudes");
            helper.setText(EmailTemplates.ActiveRequestTemplate(countRequest,namePet),true);
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
