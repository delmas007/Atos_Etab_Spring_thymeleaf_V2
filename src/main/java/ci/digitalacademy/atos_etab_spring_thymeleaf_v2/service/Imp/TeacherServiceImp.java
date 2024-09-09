package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Gender;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.TeacherRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.TeatcherMapperr;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.TeacherService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.TeacherDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImp implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeatcherMapperr teacherMapperr;

    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) {
        return teacherMapperr.fromEntity(teacherRepository.save(teacherMapperr.toEntity(teacherDTO)));
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO) {
        return findOne(teacherDTO.getId()).map(existingAddress -> {
            existingAddress.setFirstName(teacherDTO.getFirstName());
            existingAddress.setLastName(teacherDTO.getLastName());
            return save(existingAddress);
        }).orElseThrow(() -> new RuntimeException("Nom not found"));
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO, Long id) {
        teacherDTO.setId(id);
        return update(teacherDTO);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherDTO> getAll() {
        return teacherRepository.findAll().stream().map(address -> {
            return teacherMapperr.fromEntity(address);
        }).toList();
    }

    @Override
    public Optional<TeacherDTO> findOne(Long id) {
        return teacherRepository.findById(id).map(address -> {
            return teacherMapperr.fromEntity(address);
        });
    }

    @Override
    public List<TeacherDTO> findByLastNameOrSpecialtyAndGender(String query,String gender) {
        return teacherRepository.findByLastNameOrSpecialtyAndGender(query,query,Gender.valueOf(gender)).stream().map(
                teacher -> {
                    return teacherMapperr.fromEntity(teacher);
                }).toList();
    }
}
