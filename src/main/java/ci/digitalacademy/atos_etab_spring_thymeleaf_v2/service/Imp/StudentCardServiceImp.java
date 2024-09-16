package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.StudentCardRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.StudentCardMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.StudentCardService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.StudentService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentCardDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentCardServiceImp implements StudentCardService {
    private final StudentCardMapper studentCardMapper;
    private final StudentCardRepository studentRepository;
    private final StudentService studentService;
    @Override
    public StudentCardDTO save(StudentCardDTO studentCardDTO,Long id) {
        studentCardDTO.setSlug(SlugifyUtils.generate(studentCardDTO.getReference()));
        studentService.findOne(id).ifPresent(studentDTO -> {
            studentCardDTO.setStudent(studentDTO);
        });
        return studentCardMapper.fromEntity(studentRepository.save(studentCardMapper.toEntity(studentCardDTO)));
    }

    @Override
    public StudentCardDTO update(StudentCardDTO studentCardDTO) {
        return findOneById(studentCardDTO.getStudent().getId()).map(existingAddress -> {
            if (studentCardDTO.getReference() != null) {
                existingAddress.setReference(studentCardDTO.getReference());
            }
            if (studentCardDTO.getIssueDate() != null) {
                existingAddress.setIssueDate(studentCardDTO.getIssueDate());
            }
            if (studentCardDTO.getExpirationDate() != null) {
                existingAddress.setExpirationDate(studentCardDTO.getExpirationDate());
            }
            return save(existingAddress,studentCardDTO.getStudent().getId());
        }).orElseThrow(() -> new RuntimeException("Nom not found"));
    }

    @Override
    public StudentCardDTO update(StudentCardDTO studentCardDTO, Long id) {
        studentCardDTO.setId(id);
        return update(studentCardDTO);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentCardDTO> getAll() {
        return studentRepository.findAll().stream().map(address -> {
            return studentCardMapper.fromEntity(address);
        }).toList();
    }

    @Override
    public Optional<StudentCardDTO> findOneById(Long id) {
        return studentRepository.findById(id).map(address -> {
            return studentCardMapper.fromEntity(address);
        });
    }

    @Override
    public Optional<StudentCardDTO> findOneBySlug(String slug) {
        return studentRepository.findBySlug(slug).map(address -> {
            return studentCardMapper.fromEntity(address);
        });
    }
}
