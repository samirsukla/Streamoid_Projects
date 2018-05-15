package com.services.test;   
import java.util.*;  
    import javax.mail.*;  
    import javax.mail.internet.*;  
    import javax.activation.*;  
      
    public class Test1  
    {  
     public static void main(String [] args){  
          String to = "samirkumarsukla@gmail.com";
          String from = "sk.sukla@outlook.com";
          String host = "localhost";
      
         //Get the session object  
          Properties properties = System.getProperties();  
          properties.setProperty("mail.smtp.host", host);  
          Session session = Session.getDefaultInstance(properties);  
      
         //compose the message  
          try{  
             MimeMessage message = new MimeMessage(session);  
             message.setFrom(new InternetAddress(from));  
             message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
             message.setSubject("Hiiiiiiiiiiiiiii");  
             message.setText("Hello, this is example of sending email  ");  
      
             // Send message  
             Transport.send(message);  
             System.out.println("message sent successfully....");  
      
          }catch (MessagingException mex) {mex.printStackTrace();}  
       }  
    }  