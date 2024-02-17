package org.example.cxc_hrm.service;

import org.springframework.stereotype.Service;

@Service
public interface MailService {
    void sendVerifyCode(Integer code,String email);
}