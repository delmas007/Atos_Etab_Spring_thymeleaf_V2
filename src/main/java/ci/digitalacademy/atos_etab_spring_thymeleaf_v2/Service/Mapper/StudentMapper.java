package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper;


import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Student;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.StudentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {
}
