package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.StudentCards;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentCardDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentCardMapper extends EntityMapper<StudentCardDTO, StudentCards> {
}
