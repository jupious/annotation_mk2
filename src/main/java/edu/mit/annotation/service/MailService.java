package edu.mit.annotation.service;

import edu.mit.annotation.entity.EmailMessage;
import edu.mit.annotation.mailfactory.MailSenderFactory;
import jakarta.mail.BodyPart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@AllArgsConstructor
public class MailService {
    private MailSenderFactory mailSenderFactory;

    public String sendMail(EmailMessage emailMessage, String type, String email, String password){
        JavaMailSender javaMailSender = mailSenderFactory.getSender(email,password);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true,"UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo());
            mimeMessageHelper.setSubject(emailMessage.getSubject());
            mimeMessageHelper.setText(emailMessage.getMessage());
            if(emailMessage.getMultipartFile() != null){
                mimeMessageHelper.addAttachment(emailMessage.getMultipartFile().getOriginalFilename(), emailMessage.getMultipartFile());
            }
            if(emailMessage.getFilePath() != null){
                File file = new File(emailMessage.getFilePath());
                FileSystemResource fileSystemResource = new FileSystemResource(file);
                mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
            }

            javaMailSender.send(mimeMessage);

            System.out.println("성공");

            return "aaaaaa";
        }catch (Exception e){
            System.out.println("실패..");
            e.printStackTrace();
            return "아 이게 실패하네;;";
        }

    }
}
