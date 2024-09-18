package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.MailDTO;

public interface NotificationMailService {
    void sendNotificationMail(MailDTO mailDTO);
}
