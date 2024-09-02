package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDTO extends PersonDTO {
    private boolean available;
    private String specialty;
}
