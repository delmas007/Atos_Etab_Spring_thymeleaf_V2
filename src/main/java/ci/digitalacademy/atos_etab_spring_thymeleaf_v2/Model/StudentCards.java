package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model;

import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.Set;

public class StudentCards {
    private String reference;
    private Date issueDate;
    private Date expirationDate;

    @ManyToOne
    private Set<Student> student;
}
