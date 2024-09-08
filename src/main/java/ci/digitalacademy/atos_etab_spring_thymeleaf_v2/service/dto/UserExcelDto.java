package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserExcelDto {

    @ExcelProperty("Pseudo")
    private String pseudo;

    @ExcelProperty("Date de creation")
    private String creationDate;

    @ExcelProperty("Roles")
    private String roleUser;

    @ExcelProperty("Date de Naissance")
    private String school;
}
