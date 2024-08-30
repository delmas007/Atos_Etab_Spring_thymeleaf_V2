package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Absence;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.AbsenceMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.AbsenceDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AbsenceMapperImp implements AbsenceMapper {
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
