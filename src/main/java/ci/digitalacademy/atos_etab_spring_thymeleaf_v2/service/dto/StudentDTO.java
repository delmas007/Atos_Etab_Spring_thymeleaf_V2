package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Absence;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class StudentDTO extends PersonDTO {

    @ExcelProperty("Matricule")
    private String matricule;

    @ExcelProperty("Numéro de Téléphone du Père")
    private String phoneNumberFather;

    @JsonIgnore
    private Set<AbsenceDTO> absence;
}
