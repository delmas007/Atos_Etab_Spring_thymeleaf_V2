package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentCardDTO;

import java.util.List;
import java.util.Optional;

public interface StudentCardService {
    StudentCardDTO save(StudentCardDTO StudentCardDTO,Long id);
    StudentCardDTO update(StudentCardDTO StudentCardDTO);
    StudentCardDTO update(StudentCardDTO StudentCardDTO, Long id);
    void delete(Long id);
    List<StudentCardDTO> getAll();
    Optional<StudentCardDTO> findOneById(Long id);
    Optional<StudentCardDTO> findOneBySlug(String slug);
}
