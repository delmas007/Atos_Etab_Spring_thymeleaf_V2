package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Student;
import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.Set;

public class StudentCardDTO {
    private String reference;
    private Date issueDate;
    private Date expirationDate;
    private Set<Student> student;
}
