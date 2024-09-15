package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.School;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppSettingDTO {
    private Long id;
    private String slug;
    private String smtpServer;
    private Long smtpPort;
    private String smtpUsername;
    private String smtpPassword;
//    private SchoolDTO school;
}
