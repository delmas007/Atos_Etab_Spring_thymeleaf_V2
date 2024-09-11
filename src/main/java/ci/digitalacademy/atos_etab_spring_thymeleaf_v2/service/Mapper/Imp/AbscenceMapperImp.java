package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Absence;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.AbsenceMapperr;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AbsenceDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AbscenceMapperImp implements AbsenceMapperr {
    private final ModelMapper modelMapper;
    @Override
    public AbsenceDTO fromEntity(Absence entity) {
        return modelMapper.map(entity, AbsenceDTO.class);
    }

    @Override
    public Absence toEntity(AbsenceDTO dto) {
        return modelMapper.map(dto, Absence.class);
    }
}
