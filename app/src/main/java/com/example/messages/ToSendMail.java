package com.example.messages;

import android.os.AsyncTask;
import android.util.Log;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ToSendMail extends AsyncTask<String,Void,Void> {
    String recepient ;
    String smailid ;
    String spw ;

    public ToSendMail(String email,String senderId,String senderpw) {

        this.recepient =email ;
        this.smailid = senderId ;
        this.spw = senderpw ;
    }

    @Override
    protected Void doInBackground(String... strings) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(smailid,spw);
                    }
                });
        try {
            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(session);
            mm.setFrom(new InternetAddress(smailid));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            mm.setSubject("Success Message");
            mm.setText("****** Hi dude \n You got a message from android studio ********");
            //mm.setContent(textMessage ,"text/html;charset= utf-8");
            //Sending email
            Log.e("message" ,"before transport .......");
            Transport.send(mm);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

