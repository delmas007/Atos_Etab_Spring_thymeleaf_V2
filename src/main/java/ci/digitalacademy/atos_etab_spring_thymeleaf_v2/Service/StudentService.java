package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    StudentDTO save(StudentDTO studentDTO);
    StudentDTO update(StudentDTO studentDTO);
    void delete(Long id);
    List<StudentDTO> getAll();
    Optional<StudentDTO> findOne(Long id);
}
