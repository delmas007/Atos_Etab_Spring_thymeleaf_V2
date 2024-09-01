package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

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

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
