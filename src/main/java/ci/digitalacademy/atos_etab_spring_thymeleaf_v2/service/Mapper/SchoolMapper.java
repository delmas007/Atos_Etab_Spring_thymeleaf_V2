package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.School;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.SchoolDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchoolMapper extends EntityMapper<SchoolDTO, School> {
}
