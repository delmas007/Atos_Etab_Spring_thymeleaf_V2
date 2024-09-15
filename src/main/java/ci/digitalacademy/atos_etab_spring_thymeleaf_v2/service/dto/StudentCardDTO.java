package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class StudentCardDTO {
    private Long id;
    private String reference;

    private String slug;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
    private StudentDTO student;
}
