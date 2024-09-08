package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RegistrationSchoolDto extends SchoolDTO{
    private MultipartFile file;
}
