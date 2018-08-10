package com.services.utilities;


import org.testng.annotations.Test;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import javax.mail.Session;
import javax.mail.Transport;
 
 
public class SendStatusReport
{
 @Test
	public void sendMail()
   {    
	 
	 String[] sendTo = new String[] {"kinshuk@streamoid.com","hemang@streamoid.com"};
	 String[] sendCC = new String [] {"sar@streamoid.com", "prathaban@streamoid.com", "dash@streamoid.com", "naveen@streamoid.com", "samir@streamoid.com"};
	 
      Properties properties = new Properties();
      properties.put("mail.smtp.host", "smtp.gmail.com");
      properties.put("mail.smtp.socketFactory.port", "465");    
      properties.put("mail.smtp.socketFactory.class",    
                "javax.net.ssl.SSLSocketFactory");    
      properties.put("mail.smtp.auth", "true");    
      properties.put("mail.smtp.port", "465");    
      
      Session session = Session.getDefaultInstance(properties,    
              new javax.mail.Authenticator() {    
              protected PasswordAuthentication getPasswordAuthentication() {    
              return new PasswordAuthentication("samir@streamoid.com","S@mirSukla351");  
              }    
             });   
      
      try
      {
    	  InternetAddress[] mailAddressTO = new InternetAddress [sendTo.length] ;
          for(int i=0;i<sendTo.length;i++){
              mailAddressTO[i] = new InternetAddress(sendTo[i]);
          }
          InternetAddress[] mailAddressCC = new InternetAddress [sendCC.length] ;
          for(int i=0;i<sendCC.length;i++){
              mailAddressCC[i] = new InternetAddress(sendCC[i]);
          }
         MimeMessage message = new MimeMessage(session);
         message.addRecipients(Message.RecipientType.TO, mailAddressTO);
         message.addRecipients(Message.RecipientType.CC, mailAddressCC);
         message.setSubject("Similar & Outfitter Service Status Report - Common Vendors");
         
         /*Attachment Section*/
         BodyPart messageBodyPart1 = new MimeBodyPart();  
         messageBodyPart1.setText("Please find the status report attached for Similar and Outfitter services for Common Vendors."); 
         MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
         String filename = "target/surefire-reports/emailable-report.html";
         DataSource source = new FileDataSource(filename);  
         messageBodyPart2.setDataHandler(new DataHandler(source));  
         messageBodyPart2.setFileName(filename);
         Multipart multipart = new MimeMultipart();  
         multipart.addBodyPart(messageBodyPart1);  
         multipart.addBodyPart(messageBodyPart2);
         message.setContent(multipart );  
         /*Attachment Section*/
         
         Transport.send(message);
         System.out.println("Mail successfully sent");
      }
      catch (MessagingException mex) 
      {
    	  
         mex.printStackTrace();
         System.out.println("Mail Couldn't be Delivered.");
      }
   }
}
