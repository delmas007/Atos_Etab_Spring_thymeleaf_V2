package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class AbsenceDTO {
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date absenceDate;

    private Integer absenceNumber;
    private String slug;
    private StudentDTO student;
}
