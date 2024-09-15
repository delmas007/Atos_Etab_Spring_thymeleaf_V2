package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(unique = true)
    private String slug;
    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true,name = "phone_number")
    private String phoneNumber;

    @Column(name = "url_picture")
    private String urlPicture;

    @Enumerated(value=EnumType.STRING)
    private Gender gender;

    @OneToOne
    private Address address;

    @OneToOne
    private User user;
}
