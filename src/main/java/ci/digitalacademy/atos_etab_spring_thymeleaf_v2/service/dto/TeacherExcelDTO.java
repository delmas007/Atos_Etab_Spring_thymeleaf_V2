package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TeacherExcelDTO {

    @ExcelProperty("Date de Naissance")
    private LocalDate birthday;

    @ExcelProperty("Disponibilite")
    private String available;

    @ExcelProperty("Specialite")
    private String specialty;

    @ExcelProperty("Genre")
    private String gender;

    @ExcelProperty("Numéro de Téléphone")
    private String phoneNumber;

    @ExcelProperty("Prénom")
    private String firstName;

    @ExcelProperty("Nom")
    private String lastName;
}
