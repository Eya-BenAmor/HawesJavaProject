/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Yahya
 */
public class Email {
    	
	public static void sendEmail(String address, String subject, String message) throws Exception{
                        
            String from="yahya.bensalah20@gmail.com";
            String pass="20415519yahya";
            String[] to = {address};
            String host="smtp.gmail.com";
            
            Properties prop = System.getProperties();
            prop.put("mail.smtp.starttls.enable","true");
            prop.put("mail.smtp.host",host);
            prop.put("mail.smtp.user",from);
            prop.put("mail.smtp.password",pass);
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth","true");
            
            Session session = Session.getDefaultInstance(prop);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            
            InternetAddress[] toaddress = new InternetAddress[to.length];
            for(int i=0;i<to.length;i++){
                toaddress[i] = new InternetAddress(to[i]);
            }
            for(int i=0;i<toaddress.length;i++){
                msg.setRecipient(Message.RecipientType.TO, toaddress[i]);
            }
            msg.setSubject(subject);
            msg.setContent(message,"test/html ; charset=utf-8");
            msg.setText(message,"UTF-8");
            Transport transport=session.getTransport("smtp");
            transport.connect(host,from,pass);
            transport.sendMessage(msg,msg.getAllRecipients());
            transport.close();
            
            
            
            

        }}
