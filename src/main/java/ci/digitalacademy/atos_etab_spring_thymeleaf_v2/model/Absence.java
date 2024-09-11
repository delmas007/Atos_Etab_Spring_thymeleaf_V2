package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Date absenceDate;
    private Integer absenceNumber;

    @Column(unique = true)
    private String slug;

    @ManyToOne
    private Student student;


}
