package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String slug;
    private String name;
    private String logo;

    @OneToOne
    private AppSetting appSetting;
}
