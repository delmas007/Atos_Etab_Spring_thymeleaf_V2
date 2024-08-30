package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class RoleUserDTO {
    private Long id;
    private String role;
}
