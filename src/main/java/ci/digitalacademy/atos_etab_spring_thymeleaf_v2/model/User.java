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
@Table(name = "utilisateur")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(unique = true)
    private String slug;
    private String pseudo;
    private String password;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean active = true;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleUser> roleUser;

    @ManyToOne
    private School school;

}
