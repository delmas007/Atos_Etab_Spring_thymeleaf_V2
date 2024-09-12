package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationStudentDTO {
    private String firstName;
    private String lastName;
    private String matricule;
    private String email;
    private String country;
    private String city;
    private String psuedo;
    private String street;
}
