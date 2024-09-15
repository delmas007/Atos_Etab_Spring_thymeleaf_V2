package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationUserDTO extends UserDTO{
    private String role;
}
