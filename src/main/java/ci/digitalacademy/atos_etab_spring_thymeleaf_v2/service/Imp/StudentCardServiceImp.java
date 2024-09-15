package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.StudentCardRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.StudentCardMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.StudentCardService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentCardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentCardServiceImp implements StudentCardService {
    private final StudentCardMapper studentCardMapper;
    private final StudentCardRepository studentRepository;
    @Override
    public StudentCardDTO save(StudentCardDTO studentCardDTO) {
        return studentCardMapper.fromEntity(studentRepository.save(studentCardMapper.toEntity(studentCardDTO)));
    }

    @Override
    public StudentCardDTO update(StudentCardDTO studentCardDTO) {
        return findOne(studentCardDTO.getStudent().getId()).map(existingAddress -> {
            existingAddress.setReference(studentCardDTO.getReference());
            existingAddress.setIssueDate(studentCardDTO.getIssueDate());
            return save(existingAddress);
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
    public Optional<StudentCardDTO> findOne(Long id) {
        return studentRepository.findById(id).map(address -> {
            return studentCardMapper.fromEntity(address);
        });
    }
}
