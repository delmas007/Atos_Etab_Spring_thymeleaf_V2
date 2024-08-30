package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Repository.TeacherRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.TeacherMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.TeacherService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.TeacherDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImp implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) {
        return teacherMapper.fromEntity(teacherRepository.save(teacherMapper.toEntity(teacherDTO)));
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
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherDTO> getAll() {
        return teacherRepository.findAll().stream().map(address -> {
            return teacherMapper.fromEntity(address);
        }).toList();
    }

    @Override
    public Optional<TeacherDTO> findOne(Long id) {
        return teacherRepository.findById(id).map(address -> {
            return teacherMapper.fromEntity(address);
        });
    }
}
