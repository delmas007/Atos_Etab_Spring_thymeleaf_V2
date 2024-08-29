package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Student;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class AbsenceDTO {
    private Date absenceDate;
    private int absenceNumber;
    private Student student;
}
