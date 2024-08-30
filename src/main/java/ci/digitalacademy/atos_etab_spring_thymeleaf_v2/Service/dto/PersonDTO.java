package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Address;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Gender;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public  class PersonDTO {
    private Long id ;
    private Instant birthday;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String urlPicture;
    private Gender gender;
    private Address address;
    private User user;


}
