package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Absence;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AbsenceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AbsenceMapper extends EntityMapper<AbsenceDTO, Absence> {
}
