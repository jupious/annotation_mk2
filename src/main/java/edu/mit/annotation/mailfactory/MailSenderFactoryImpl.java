package edu.mit.annotation.mailfactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MailSenderFactoryImpl implements MailSenderFactory{
    @Value("smtp.gmail.com")
    private String host;

    @Value("587")
    private int port;

    @Value("true")
    private String auth;

    @Value("true")
    private String enable;

    @Value("UTF-8")
    private String charset;

    @Value("smtp")
    private String protocol;

    @Override
    public JavaMailSender getSender(String email, String password) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setUsername(email);
        mailSender.setPassword(password);
        mailSender.setPort(port);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth",auth);
        props.put("mail.smtp.starttls.enable",enable);
        props.put("mail.mime.charset",charset);
        props.put("mail.transport.protocol",protocol);
        return mailSender;
    }
}
