package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Student;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.StudentMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapperImp implements StudentMapper {

    private final ModelMapper modelMapper;

    @Override
    public StudentDTO fromEntity(Student entity) {
        return modelMapper.map(entity, StudentDTO.class);
    }

    @Override
    public Student toEntity(StudentDTO dto) {
        return modelMapper.map(dto, Student.class);
    }
}
