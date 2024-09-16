package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.TeacherDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TeacherService {
    TeacherDTO saveUserAndTeacher(TeacherDTO TeacherDTO);
    TeacherDTO save(TeacherDTO TeacherDTO);
    TeacherDTO uploadTeacherPicture(Long id , MultipartFile file) throws IOException;
    TeacherDTO update(TeacherDTO TeacherDTO);
    TeacherDTO update(TeacherDTO TeacherDTO, Long id);
    void delete(Long id);
    List<TeacherDTO> getAll();
    Optional<TeacherDTO> findOne(Long id);
    List<TeacherDTO> findByLastNameOrSpecialtyAndGender(String query, String gender);
}
