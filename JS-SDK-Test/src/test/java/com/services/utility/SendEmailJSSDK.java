package com.services.utility;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import javax.mail.Session;
import javax.mail.Transport;
 
 
public class SendEmailJSSDK
{
 @Test
	public void sendMail() throws FileNotFoundException, IOException
   {    
	 
	 String[] sendTo = new String[] {"murtaza.ali@streamoid.com","vivekb@streamoid.com"};
	 String[] sendCC = new String [] {"sar@streamoid.com", "prathaban@streamoid.com", "dash@streamoid.com", "naveen@streamoid.com", "samir@streamoid.com","kinshuk@streamoid.com","hemang@streamoid.com","malini@streamoid.com"};
	 
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
         message.setSubject("JS-SDK Test Automation Report");
         
         /* HTML Embedded Mail Body */
//         BodyPart messageBodyPart1 = new MimeBodyPart();  
//         messageBodyPart1.setText("Please find the status report attached for Similar and Outfitter services for Common Vendors."); 
         StringWriter writer = new StringWriter();
         IOUtils.copy(new FileInputStream(new File("target/surefire-reports/emailable-report.html")), writer, "ISO-8859-1");
         MimeBodyPart messageBodyPart = new MimeBodyPart();
         messageBodyPart.setContent(writer.toString(), "text/html");
         Multipart multipart = new MimeMultipart();
//         multipart.addBodyPart(messageBodyPart1);
         multipart.addBodyPart(messageBodyPart);
         message.setContent(multipart); 
         /* HTML Embeded Mail Body */
         
         /*Attachment Section*/
//         BodyPart messageBodyPart1 = new MimeBodyPart();  
//         messageBodyPart1.setText("Please find the status report attached for JS-SDK Test Automation.."); 
//         MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
//         String filename = "target/surefire-reports/emailable-report.html";
//         DataSource source = new FileDataSource(filename);  
//         messageBodyPart2.setDataHandler(new DataHandler(source));  
//         messageBodyPart2.setFileName(filename);
//         Multipart multipart = new MimeMultipart();  
//         multipart.addBodyPart(messageBodyPart1);  
//         multipart.addBodyPart(messageBodyPart2);
//         message.setContent(messageBodyPart2, "text/html; charset=utf-8");
//         message.setContent(multipart );  
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

