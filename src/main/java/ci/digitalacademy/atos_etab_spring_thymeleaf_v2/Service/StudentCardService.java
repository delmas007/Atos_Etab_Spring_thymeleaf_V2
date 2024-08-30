package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.StudentCardDTO;

import java.util.List;
import java.util.Optional;

public interface StudentCardService {
    StudentCardDTO save(StudentCardDTO StudentCardDTO);
    StudentCardDTO update(StudentCardDTO StudentCardDTO);
    void delete(Long id);
    List<StudentCardDTO> getAll();
    Optional<StudentCardDTO> findOne(Long id);
}
