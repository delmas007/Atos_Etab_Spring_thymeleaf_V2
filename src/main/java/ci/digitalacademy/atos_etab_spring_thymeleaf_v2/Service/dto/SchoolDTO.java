package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.AppSetting;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.User;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public class SchoolDTO {
    private Long id;
    private String name;
    private String logo;
    private User user;
    private AppSetting appSetting;
}
