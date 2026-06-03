package com.fundoonotes.fundoo_notes.service.impl;

import com.fundoonotes.fundoo_notes.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}") // Reuse mail username as SendGrid verified sender
    private String fromEmail;

    @Value("${SENDGRID_API_KEY:}")
    private String sendGridApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendVerificationEmail(String toEmail, String token) {
        String link = "http://localhost:8080" +
                "/api/users/verify?token=" + token;
        sendEmail(toEmail,
                "Verify Your Fundoo Notes Account",
                "Hello,\n\nClick to verify your account:\n\n"
                        + link + "\n\nThis link expires in 24 hours.\n\n"
                        + "Regards,\nFundoo Notes Team");
    }

    @Override
    public void sendPasswordResetEmail(String toEmail, String token) {
        String link = "http://localhost:8080" +
                "/api/users/reset-password?token=" + token;
        sendEmail(toEmail,
                "Reset Your Fundoo Notes Password",
                "Hello,\n\nClick to reset your password:\n\n"
                        + link + "\n\nThis link expires in 24 hours.\n\n"
                        + "Regards,\nFundoo Notes Team");
    }

    @Override
    public void sendReminderEmail(String toEmail, String noteTitle) {
        sendEmail(toEmail,
                "Reminder: " + noteTitle,
                "Hello,\n\nThis is a reminder for your note:\n\n\""
                        + noteTitle + "\"\n\nPlease check your Fundoo Notes.\n\n"
                        + "Regards,\nFundoo Notes Team");
    }

    @Override
    public void sendOtpEmail(String toEmail, String otp) {
        sendEmail(toEmail,
                "Verify Your Fundoo Notes Account - OTP",
                "Hello,\n\n" +
                        "Your OTP for account verification is:\n\n" +
                        "🔐 " + otp + "\n\n" +
                        "This OTP is valid for 10 minutes.\n\n" +
                        "If you did not register, please ignore this email.\n\n" +
                        "Regards,\nFundoo Notes Team"
        );
    }

    @Override
    public void sendPasswordResetOtpEmail(String toEmail, String otp) {
        sendEmail(toEmail,
                "Reset Your Fundoo Notes Password - OTP",
                "Hello,\n\n" +
                        "Your OTP for password reset is:\n\n" +
                        "🔐 " + otp + "\n\n" +
                        "This OTP is valid for 10 minutes.\n\n" +
                        "If you did not request this, please ignore this email.\n\n" +
                        "Regards,\nFundoo Notes Team"
        );
    }

    private void sendEmail(String to,
                           String subject,
                           String body) {
        if (sendGridApiKey == null || sendGridApiKey.trim().isEmpty()) {
            System.out.println("=================================================");
            System.out.println("SENDGRID_API_KEY not set. Printing email to console:");
            System.out.println("TO: " + to);
            System.out.println("SUBJECT: " + subject);
            System.out.println("BODY:\n" + body);
            System.out.println("=================================================");
            return;
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(sendGridApiKey);

            // Construct JSON request body for SendGrid v3 Mail Send API
            Map<String, Object> request = new HashMap<>();
            
            Map<String, Object> toMap = new HashMap<>();
            toMap.put("email", to);
            
            Map<String, Object> personalization = new HashMap<>();
            personalization.put("to", List.of(toMap));
            
            request.put("personalizations", List.of(personalization));
            
            Map<String, Object> fromMap = new HashMap<>();
            fromMap.put("email", fromEmail);
            request.put("from", fromMap);
            
            request.put("subject", subject);
            
            Map<String, Object> contentMap = new HashMap<>();
            contentMap.put("type", "text/plain");
            contentMap.put("value", body);
            request.put("content", List.of(contentMap));

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
            String url = "https://api.sendgrid.com/v3/mail/send";
            restTemplate.postForEntity(url, entity, String.class);
            System.out.println("Email successfully sent to " + to + " via SendGrid HTTPS API!");
        } catch (Exception e) {
            System.err.println("Failed to send email via SendGrid: " + e.getMessage());
            throw new RuntimeException("Email delivery failed: " + e.getMessage());
        }
    }
}
