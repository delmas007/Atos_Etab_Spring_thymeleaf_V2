package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student extends Person  {

    @Column(name = "matricule", unique = true)
    private String matricule;
    private String phoneNumberFather;


    @OneToMany(mappedBy = "student")
    private Set<Absence> absence;


}