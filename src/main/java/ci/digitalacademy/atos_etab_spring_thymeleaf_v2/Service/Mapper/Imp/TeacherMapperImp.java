package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Teacher;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.TeacherMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.TeacherDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherMapperImp implements TeacherMapper {
    private final ModelMapper modelMapper;

    @Override
    public TeacherDTO fromEntity(Teacher entity) {
        return modelMapper.map(entity, TeacherDTO.class);
    }

    @Override
    public Teacher toEntity(TeacherDTO dto) {
        return modelMapper.map(dto, Teacher.class);
    }
}
