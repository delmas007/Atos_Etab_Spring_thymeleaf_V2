package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String pseudo;
    private String password;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<RoleUser> roleUser;

    @ManyToOne
    private School school;

}
