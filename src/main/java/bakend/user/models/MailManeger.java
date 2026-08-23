package bakend.user.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import bakend.user.infra.MessageHtml;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Component
public class MailManeger {

    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;
    public MailManeger( JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;

    }

    public void sendMessage(String email,String messageEmail){
        MimeMessage message= javaMailSender.createMimeMessage();
        MessageHtml messageHtml= new MessageHtml();
       

        
        try{
            message.setSubject("sad");
            MimeMessageHelper helper= new MimeMessageHelper(message);
            helper.setTo(email);
            helper.setText( messageHtml.getTemplate("jose", email, email, messageEmail),true);
            helper.setFrom(sender);

            javaMailSender.send(message);


            
        }catch( MessagingException e){
            throw new RuntimeException(e);

        }   
    }


}
