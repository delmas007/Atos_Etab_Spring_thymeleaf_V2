package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Absence;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.StudentCards;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class StudentDTO extends PersonDTO {
    private String matricule;
    private String phoneNumberFather;
    private Set<Absence> absence;
}
