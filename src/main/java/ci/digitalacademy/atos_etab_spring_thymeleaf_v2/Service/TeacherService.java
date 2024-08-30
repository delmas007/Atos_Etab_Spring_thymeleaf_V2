package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.TeacherDTO;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    TeacherDTO save(TeacherDTO TeacherDTO);
    TeacherDTO update(TeacherDTO TeacherDTO);
    void delete(Long id);
    List<TeacherDTO> getAll();
    Optional<TeacherDTO> findOne(Long id);
}
