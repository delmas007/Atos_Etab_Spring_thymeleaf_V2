package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.RoleUser;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.School;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id ;
    private String pseudo;
    private String password;
    private Date creationDate;
    private boolean isActive;
    private Set<RoleUserDTO> roleUser;
    private SchoolDTO school;
}
