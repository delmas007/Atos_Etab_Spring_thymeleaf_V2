package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class StudentCards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String slug;
    private String reference;
    private LocalDate issueDate;
    private LocalDate expirationDate;

    @ManyToOne
    private Student student;
}
