package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.AppSetting;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolDTO {
    private Long id;
    private String name;
    private String logo;
    private AppSetting appSetting;
}
