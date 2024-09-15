package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Address;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Gender;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.User;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public  class PersonDTO {

    @ExcelProperty("ID")
    private Long id ;
    private String slug;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @ExcelProperty("Prénom")
    private String firstName;
    @ExcelProperty("Nom")
    private String lastName;

    @ExcelProperty("Numéro de Téléphone")
    private String phoneNumber;

    private String urlPicture;

    @ExcelProperty("Genre")
    private Gender gender;
    private AddressDTO address;
    private UserDTO user;


}
