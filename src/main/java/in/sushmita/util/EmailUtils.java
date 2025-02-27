package in.sushmita.util;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String subject, String body,String to)
    {
        boolean isSent =false;

        try {
            MimeMessage mimeMsg = mailSender.createMimeMessage();

            MimeMessageHelper helper= new MimeMessageHelper(mimeMsg);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);
            mailSender.send(mimeMsg);
            isSent=true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSent;
        }
    }

