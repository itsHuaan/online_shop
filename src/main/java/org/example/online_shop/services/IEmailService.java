package org.example.online_shop.services;

import org.example.online_shop.models.EmailModel;
import org.springframework.core.io.ByteArrayResource;

public interface IEmailService {
    boolean send(EmailModel email);
    boolean sendWithAttachment(EmailModel email,String fileName, ByteArrayResource attachment);
}
