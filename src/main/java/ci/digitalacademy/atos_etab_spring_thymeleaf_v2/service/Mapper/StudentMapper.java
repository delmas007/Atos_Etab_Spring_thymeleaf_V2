package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper;


import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Student;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {
}
