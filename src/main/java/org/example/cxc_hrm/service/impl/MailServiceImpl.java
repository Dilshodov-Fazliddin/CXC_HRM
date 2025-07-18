package org.example.cxc_hrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.cxc_hrm.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String sender;
   private final JavaMailSender javaMailSender;

    @Override
    public void sendVerifyCode(Integer code, String email) {
        String message = "Your verification code 👩‍💻  " + code;
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Verification");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);
    }


}
