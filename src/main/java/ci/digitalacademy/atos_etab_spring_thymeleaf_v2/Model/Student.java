package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


public class Student extends Person  {
    private String matricule;
    private String phoneNumberFather;

    private StudentCards studentCards;
    private List<Absence> absences;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private Absence absence;

    @OneToMany
    private StudentCards studentCard;
}