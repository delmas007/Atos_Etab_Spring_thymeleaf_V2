package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.School;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.SchoolMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.AppSettingDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.SchoolDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolMapperImp implements SchoolMapper {
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
