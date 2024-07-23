package ozdemirozdemir.backend.service;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ozdemirozdemir.backend.exception.EmailFailedToSendException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class MailService {

    private final Gmail gmail;

    public void sendEmail(String to, String subject, String content) throws Exception {

        Properties properties = new Properties();

        Session session = Session.getInstance(properties, null);

        MimeMessage email = new MimeMessage(session);

        try {
            email.setFrom(new InternetAddress("ozdemirpossible@gmail.com"));
            email.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            email.setSubject(subject);
            email.setText(content);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            email.writeTo(byteArrayOutputStream);

            byte[] rawMessageBytes = byteArrayOutputStream.toByteArray();

            String encodedEmail = Base64.getEncoder().encodeToString(rawMessageBytes);

            Message message = new Message();

            message.setRaw(encodedEmail);

            message = gmail.users().messages().send("me", message).execute();
        } catch (MessagingException | IOException e) {
            throw new EmailFailedToSendException(e);
        }

    }
}
