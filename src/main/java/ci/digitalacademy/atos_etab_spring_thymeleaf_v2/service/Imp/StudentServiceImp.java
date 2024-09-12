package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Gender;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.StudentRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.security.AuthorityConstants;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AddressService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.StudentMapperr;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.RoleUserService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.StudentService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.UserService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImp implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImp.class);
    private final StudentRepository studentRepository;
    private final StudentMapperr studentMapper;
    private final AddressService addressService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    private final RoleUserService roleUserService;
//    private final StudentMapper studentMapper;

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        return studentMapper.fromEntity(studentRepository.save(studentMapper.toEntity(studentDTO)));
    }

    @Override
    public StudentDTO update(StudentDTO student) {
        return findOne(student.getId()).map(existingAddress -> {
            existingAddress.setFirstName(student.getFirstName());
            existingAddress.setLastName(student.getLastName());
            return save(existingAddress);
        }).orElseThrow(() -> new RuntimeException("Nom not found"));
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO, Long id) {
        return findOne(id).map(studentDTO1 -> {
            studentDTO1.setFirstName(studentDTO.getFirstName());
            studentDTO1.setLastName(studentDTO.getLastName());
            studentDTO1.setGender(studentDTO1.getGender());
            studentDTO1.setMatricule(studentDTO.getMatricule());
            studentDTO1.setPhoneNumberFather(studentDTO.getPhoneNumberFather());
            return save(studentDTO1);
    }).orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDTO> getAll() {
        return studentRepository.findAll().stream().map(address -> {
            return studentMapper.fromEntity(address);
        }).toList();
    }

    @Override
    public Optional<StudentDTO> findOne(Long id) {
        return studentRepository.findById(id).map(address -> {
            return studentMapper.fromEntity(address);
        });
    }

    @Override
    public List<StudentDTO> findByLastNameOrGenderOrMatricule(String query, String gender) {
        return studentRepository.findByLastNameIgnoreCaseOrMatriculeIgnoreCaseOrGender(query,query,Gender.valueOf(gender)).stream().map(
                address -> {
            return studentMapper.fromEntity(address);
        }).toList();
    }

    @Override
    @Transactional
    public ResponseRegisterStudentDTO registrationStudent(RegistrationStudentDTO registrationStudentDTO) {

        AddressDTO address = modelMapper.map(registrationStudentDTO, AddressDTO.class);
        address =  addressService.save(address);

        Set<RoleUserDTO> roleUserDTOS = roleUserService.findByRole(AuthorityConstants.USER);
        UserDTO user = modelMapper.map(registrationStudentDTO, UserDTO.class);
        String password = UUID.randomUUID().toString();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRoleUser(roleUserDTOS);
        user = userService.save(user);

        StudentDTO student = modelMapper.map(registrationStudentDTO, StudentDTO.class);
        student.setUser(user);
        student.setAddress(address);
        student = save(student);

        ResponseRegisterStudentDTO response = new ResponseRegisterStudentDTO();
        response.setAddress(address);
        response.setStudent(student);
        return response;
    }
}
