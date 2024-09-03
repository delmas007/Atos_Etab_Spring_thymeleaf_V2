package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Absence;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Gender;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
public class StudentExcelDTO {

    @ExcelProperty("Date de Naissance")
    private LocalDate birthday;

    @ExcelProperty("Matricule")
    private String matricule;

    @ExcelProperty("Numéro de Téléphone du Père")
    private String phoneNumberFather;

    @ExcelProperty("Genre")
    private String gender;

    @ExcelProperty("Numéro de Téléphone")
    private String phoneNumber;

    @ExcelProperty("Prénom")
    private String firstName;

    @ExcelProperty("Nom")
    private String lastName;
}
