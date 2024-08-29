package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class Absence {
    private Date absenceDate;
    private int absenceNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
}
