package com.campusconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired(required = false)
    private JavaMailSender emailSender;

    @Value("${spring.mail.username:noreply@campusconnect.edu}")
    private String fromEmail;

    public void sendContactMessage(String toEmail, String senderEmail, String senderName, 
                                 String itemName, String message, String itemType) {
        try {
            if (emailSender == null) {
                // Log the message instead of sending email if mail is not configured
                System.out.println("=== EMAIL WOULD BE SENT ===");
                System.out.println("To: " + toEmail);
                System.out.println("From: " + senderEmail);
                System.out.println("Subject: Someone is interested in your " + itemType + ": " + itemName);
                System.out.println("Message: " + message);
                System.out.println("=== END EMAIL ===");
                return;
            }

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(toEmail);
            mailMessage.setFrom(fromEmail);
            mailMessage.setSubject("Campus Connect: Someone is interested in your " + itemType + " - " + itemName);
            
            StringBuilder emailBody = new StringBuilder();
            emailBody.append("Hello,\n\n");
            emailBody.append("Someone has shown interest in your ").append(itemType).append(" posted on Campus Connect.\n\n");
            emailBody.append("Item: ").append(itemName).append("\n\n");
            emailBody.append("Message from ").append(senderName != null ? senderName : "a user").append(" (").append(senderEmail).append("):\n");
            emailBody.append("\"").append(message).append("\"\n\n");
            emailBody.append("You can reply directly to their email: ").append(senderEmail).append("\n\n");
            emailBody.append("Best regards,\n");
            emailBody.append("Campus Connect Team");
            
            mailMessage.setText(emailBody.toString());
            emailSender.send(mailMessage);
            
        } catch (Exception e) {
            // Log error but don't fail the operation
            System.err.println("Failed to send email: " + e.getMessage());
            // Could save to a queue for retry later
        }
    }

    public void sendNotificationEmail(String toEmail, String subject, String content) {
        try {
            if (emailSender == null) {
                System.out.println("=== NOTIFICATION EMAIL ===");
                System.out.println("To: " + toEmail);
                System.out.println("Subject: " + subject);
                System.out.println("Content: " + content);
                System.out.println("=== END EMAIL ===");
                return;
            }

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(toEmail);
            mailMessage.setFrom(fromEmail);
            mailMessage.setSubject("Campus Connect: " + subject);
            mailMessage.setText(content);
            emailSender.send(mailMessage);
            
        } catch (Exception e) {
            System.err.println("Failed to send notification email: " + e.getMessage());
        }
    }
}