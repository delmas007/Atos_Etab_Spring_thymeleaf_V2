package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.RoleUser;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.School;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String pseudo;
    private String password;
    private Date creationDate;
    private RoleUser roleUser;
    private Set<School> school;
}
