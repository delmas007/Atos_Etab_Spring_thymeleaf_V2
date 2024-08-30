package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.StudentCards;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Repository.StudentCardRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Repository.StudentRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.StudentCardMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.StudentMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.StudentCardService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.StudentCardDTO;
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
//        return findOne(studentCardDTO.getStudent().).map(existingAddress -> {
//            existingAddress.setFirstName(studentCardDTO.getFirstName());
//            existingAddress.setLastName(studentCardDTO.getLastName());
//            return save(existingAddress);
//        }).orElseThrow(() -> new RuntimeException("Nom not found"));
        return null;
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
