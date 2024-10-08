package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.RegistrationStudentDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.ResponseRegisterStudentDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    StudentDTO saveUserAndStudent(StudentDTO studentDTO);
    StudentDTO save(StudentDTO studentDTO);
    StudentDTO uploadStudentPicture(Long id, MultipartFile picture) throws IOException;
    StudentDTO update(StudentDTO studentDTO);
    StudentDTO update(StudentDTO studentDTO, Long id);
    void delete(Long id);
    List<StudentDTO> getAll();
    Optional<StudentDTO> findOne(Long id);
    List<StudentDTO> findByLastNameOrGenderOrMatricule(String query, String gender);

    ResponseRegisterStudentDTO registrationStudent(RegistrationStudentDTO registrationStudentDTO);
}
