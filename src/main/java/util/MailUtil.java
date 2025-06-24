package util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

import java.util.Properties;

/**
 * Utility class for sending OTP emails with HTML styling.
 */
public class MailUtil {

    private static final String FROM_EMAIL = "duongankiemdz@gmail.com";
    private static final String APP_PASSWORD = "vfviafcvijutxqmz"; // 🔐 Recommend moving to config/env in production

    /**
     * Sends an OTP email to a user using Gmail SMTP.
     *
     * @param toEmail Recipient's email address
     * @param otp One-time password (6 digits)
     * @throws MessagingException If sending fails
     */
    public static void sendOTP(String toEmail, String otp) throws MessagingException, UnsupportedEncodingException {
        // Step 1: Configure Gmail SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Step 2: Authenticate Gmail session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        });

        // Step 3: Create the email content
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_EMAIL, "ReadTopia Support"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("🔐 Your ReadTopia OTP Code");

        String htmlContent
                = "<html>"
                + "<head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; background-color: #f6f6f6; padding: 20px; }"
                + ".container { background-color: #fff; padding: 20px; border-radius: 10px; max-width: 500px; margin: auto; box-shadow: 0 0 10px rgba(0,0,0,0.1); }"
                + ".otp { font-size: 32px; font-weight: bold; color: #4caf50; letter-spacing: 3px; margin: 20px 0; }"
                + ".footer { font-size: 12px; color: #888; margin-top: 30px; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<h2>📨 OTP Verification</h2>"
                + "<p>Hello,</p>"
                + "<p>You requested to reset your password on <strong>ReadTopia</strong>.</p>"
                + "<p>Your OTP code is:</p>"
                + "<div class='otp'>" + otp + "</div>"
                + "<p>This code will expire in 5 minutes.</p>"
                + "<p>If you didn't request this, please ignore this email.</p>"
                + "<div class='footer'>Thanks,<br>ReadTopia Team</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        message.setContent(htmlContent, "text/html; charset=utf-8");

        // Step 4: Send email
        Transport.send(message);
    }
}
