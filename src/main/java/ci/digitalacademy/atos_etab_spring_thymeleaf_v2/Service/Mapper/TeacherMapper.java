package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Teacher;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.TeacherDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper extends EntityMapper<TeacherDTO, Teacher> {
}
