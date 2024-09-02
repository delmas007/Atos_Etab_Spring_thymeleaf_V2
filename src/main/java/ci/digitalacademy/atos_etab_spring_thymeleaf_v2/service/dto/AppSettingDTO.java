package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.School;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppSettingDTO {
    private Long id;
    private String smtp_Server;
    private int smtp_Port;
    private String smtp_Username;
    private String smtp_Password;
    private School school;
}
