package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Teacher extends Person {
    private boolean available;
    private String specialty;
}
