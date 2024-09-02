package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.School;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.SchoolMapperr;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.SchoolDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolMapperImp implements SchoolMapperr {
    private final ModelMapper modelMapper;
    @Override
    public SchoolDTO fromEntity(School entity) {
        return modelMapper.map(entity, SchoolDTO.class);
    }

    @Override
    public School toEntity(SchoolDTO dto) {
        return modelMapper.map(dto, School.class);
    }
}
