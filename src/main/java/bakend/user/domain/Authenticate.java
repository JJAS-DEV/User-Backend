package bakend.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import bakend.user.models.MailManeger;
@Service
public class Authenticate {
    MailManeger mailManeger;

    public Authenticate( MailManeger mailManeger){
        this.mailManeger= mailManeger;
    }





    public void sendMessegeUser(String email, String message){

        mailManeger.sendMessage(email, message);


    }

}
