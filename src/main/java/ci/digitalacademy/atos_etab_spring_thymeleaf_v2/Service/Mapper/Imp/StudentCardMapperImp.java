package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.StudentCards;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.StudentCardMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.StudentCardDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentCardMapperImp implements StudentCardMapper {
    private final ModelMapper modelMapper;

    @Override
    public StudentCardDTO fromEntity(StudentCards entity) {
        return modelMapper.map(entity, StudentCardDTO.class);
    }

    @Override
    public StudentCards toEntity(StudentCardDTO dto) {
        return modelMapper.map(dto, StudentCards.class);
    }
}
