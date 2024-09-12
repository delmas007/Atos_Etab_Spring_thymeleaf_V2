package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseRegisterStudentDTO {

    @JsonIgnoreProperties({"phoneNumberFather","absence","id","phoneNumber","urlPicture","gender","address","user","birthday"})
    private StudentDTO student;

    @JsonIgnoreProperties({"id"})
    private AddressDTO address;
}
