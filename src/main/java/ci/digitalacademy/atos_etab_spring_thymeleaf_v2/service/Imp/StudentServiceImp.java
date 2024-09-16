package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Gender;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.StudentRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.security.AuthorityConstants;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.*;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.StudentMapperr;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.*;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.utils.SlugifyUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private final FileStorageService fileStorageService;
    private final SchoolService schoolService;
//    private final StudentMapper studentMapper;

    @Override
    public StudentDTO saveUserAndStudent(StudentDTO studentDTO) {
        SchoolDTO schoolDTO = schoolService.getAll().stream().findFirst().orElse(null);
        studentDTO.setSlug(SlugifyUtils.generate(studentDTO.getFirstName()));
        Set<RoleUserDTO> role = roleUserService.findByRole("USER");
        UserDTO user = new UserDTO();
        user.setRoleUser(role);
        user.setSchool(schoolDTO);
        user.setPseudo(studentDTO.getMatricule());
        user.setPassword(bCryptPasswordEncoder.encode(studentDTO.getMatricule()));
        user = userService.save(user);
        studentDTO.setUser(user);
        return studentMapper.fromEntity(studentRepository.save(studentMapper.toEntity(studentDTO)));
    }

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        return studentMapper.fromEntity(studentRepository.save(studentMapper.toEntity(studentDTO)));
    }

    @Override
    public StudentDTO uploadStudentPicture(Long id, MultipartFile picture) throws IOException {
        Optional<StudentDTO> one = findOne(id);
        StudentDTO student = one.orElse(null);
        if(student != null){
            String upload = fileStorageService.upload(picture);
            student.setUrlPicture(upload);
            save(student);
        }
        return student;
    }

    @Override
    public StudentDTO update(StudentDTO student) {
        return findOne(student.getId()).map(existingAddress -> {
            existingAddress.setFirstName(student.getFirstName());
            existingAddress.setLastName(student.getLastName());
            return saveUserAndStudent(existingAddress);
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
            return saveUserAndStudent(studentDTO1);
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
        student = saveUserAndStudent(student);

        ResponseRegisterStudentDTO response = new ResponseRegisterStudentDTO();
        response.setAddress(address);
        response.setStudent(student);
        return response;
    }
}
