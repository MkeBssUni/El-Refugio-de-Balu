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
    private static final String Utf8Encoding = "UTF-8";
    public void sendEmailNewAccount (String email, String code) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, Utf8Encoding);
            helper.setTo(email);
            helper.setSubject("Activar cuenta");
            helper.setText(EmailTemplates.mailTemplate("Haz sido registrado correctamente en El Refugio de Balu, usa el siguiente código para activar tu cuenta: <b>"+code+"</b>"),true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendNewCode(String email, String newPassword){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, Utf8Encoding);
            helper.setTo(email);
            helper.setSubject("Código de confirmación");
            helper.setText(EmailTemplates.mailTemplate("Hemos recibido una solicitud para cambiar tu contraseña, usa el siguiente código para confirmar tu identidad: <b>"+newPassword+"</b>"),true);
            javaMailSender.send(message);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void passwordChanged(String email){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, Utf8Encoding);
            helper.setTo(email);
            helper.setSubject("Cambio de contraseña");
            helper.setText(EmailTemplates.mailTemplate("Tu contraseña ha sido cambiada exitosamente."),true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAdoptionApprovalTemplate(String email, String namePet){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, Utf8Encoding);
            helper.setTo(email);
            helper.setSubject("¡Felicidades! se acepto tu solicitud de "+namePet);
            helper.setText(EmailTemplates.mailTemplate("Su solicitud de adopción de "+namePet+" ha sido aprobada, por favor comuníquese con nosotros para coordinar la entrega."),true);
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void finalizeAdoptionTemplate(String email, String namePet){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, Utf8Encoding);
            helper.setTo(email);
            helper.setSubject("¡Malas noticias!");
            helper.setText(EmailTemplates.mailTemplate("Su solicitud de adopción de "+namePet+" ha sido finalizada"+namePet+"."),true);
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void activeRequestTemplate(String email,String namePet,int countRequest){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, Utf8Encoding);
            helper.setTo(email);
            helper.setSubject("¡Ven a revisar!");
            helper.setText(EmailTemplates.mailTemplate("Tienes <b>"+countRequest+"</b> solicitudes de adopción de <b>"+namePet+"</b> que necesitas revisar."),true);
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendPetRejectedTemplate(String email, String petName){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, Utf8Encoding);
            helper.setTo(email);
            helper.setSubject("Mascota dada de baja");
            helper.setText(EmailTemplates.mailTemplate("La mascota <b>"+petName+"</b> ha sido dada de baja de nuestro sistema."),true);
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendNotificationNewComment(String email, String petName){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, Utf8Encoding);
            helper.setTo(email);
            helper.setSubject("¡Nuevo comentario!");
            helper.setText(EmailTemplates.mailTemplate("<b>"+petName+"</b> tiene nuevos comentarios."),true);
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendPetDischargeRequest(String email, String petName){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, Utf8Encoding);
            helper.setTo(email);
            helper.setSubject("Solicitud de baja");
            helper.setText(EmailTemplates.mailTemplate("El dueño de <b>"+petName+"</b> ha solicitado dar de baja a la mascota, atiende a su petición lo más pronto posible."),true);
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void requestChangesOrAprove(String email, String message){
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, Utf8Encoding);
            helper.setTo(email);
            helper.setSubject("Solicitud de cambios");
            helper.setText(EmailTemplates.mailTemplate(message),true);
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
