package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Address;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Gender;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public  class PersonDTO {
    private Long id ;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String urlPicture;
    private Gender gender;
    private Address address;
    private User user;


}