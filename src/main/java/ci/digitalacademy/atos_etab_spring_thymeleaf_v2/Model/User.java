package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.Set;


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pseudo;
    private String password;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @OneToMany
    private RoleUser roleUser;

    @ManyToOne
    private Set<School> school;

}
