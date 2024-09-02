package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Teacher;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.TeacherDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper extends EntityMapper<TeacherDTO, Teacher> {
}
