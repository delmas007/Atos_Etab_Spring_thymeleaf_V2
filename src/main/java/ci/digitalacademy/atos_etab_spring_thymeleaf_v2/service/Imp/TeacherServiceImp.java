package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Gender;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.TeacherRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.*;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.TeatcherMapperr;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.*;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeacherServiceImp implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeatcherMapperr teacherMapperr;
    private final RoleUserService roleUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SchoolService schoolService;
    private final UserService userService;
    private final FileStorageService fileStorageService;

    @Override
    public TeacherDTO saveUserAndTeacher(TeacherDTO teacherDTO) {
        SchoolDTO schoolDTO = schoolService.getAll().stream().findFirst().orElse(null);
        teacherDTO.setSlug(SlugifyUtils.generate(teacherDTO.getFirstName()));
        Set<RoleUserDTO> role = roleUserService.findByRole("USER");
        UserDTO user = new UserDTO();
        user.setRoleUser(role);
        user.setSchool(schoolDTO);
        user.setPseudo(teacherDTO.getLastName());
        user.setPassword(bCryptPasswordEncoder.encode(teacherDTO.getLastName()));
        user = userService.save(user);
        teacherDTO.setUser(user);
        return teacherMapperr.fromEntity(teacherRepository.save(teacherMapperr.toEntity(teacherDTO)));
    }

    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) {
        return teacherMapperr.fromEntity(teacherRepository.save(teacherMapperr.toEntity(teacherDTO)));
    }

    @Override
    public TeacherDTO uploadTeacherPicture(Long id, MultipartFile picture) throws IOException {
        Optional<TeacherDTO> one = findOne(id);
        TeacherDTO teacher = one.orElse(null);
        if(teacher != null){
            String upload = fileStorageService.upload(picture);
            teacher.setUrlPicture(upload);
            save(teacher);
        }
        return teacher;
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO) {
        return findOne(teacherDTO.getId()).map(existingAddress -> {
            if (teacherDTO.getFirstName() != null) {
                existingAddress.setFirstName(teacherDTO.getFirstName());
            }
            if (teacherDTO.getLastName() != null) {
                existingAddress.setLastName(teacherDTO.getLastName());
            }
            if (teacherDTO.getSpecialty() != null) {
                existingAddress.setSpecialty(teacherDTO.getSpecialty());
            }
            return saveUserAndTeacher(existingAddress);
        }).orElseThrow(() -> new RuntimeException("Nom not found"));
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO, Long id) {
        teacherDTO.setId(id);
        return update(teacherDTO);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherDTO> getAll() {
        return teacherRepository.findAll().stream().map(address -> {
            return teacherMapperr.fromEntity(address);
        }).toList();
    }

    @Override
    public Optional<TeacherDTO> findOne(Long id) {
        return teacherRepository.findById(id).map(address -> {
            return teacherMapperr.fromEntity(address);
        });
    }

    @Override
    public List<TeacherDTO> findByLastNameOrSpecialtyAndGender(String query,String gender) {
        return teacherRepository.findByLastNameOrSpecialtyAndGender(query,query,Gender.valueOf(gender)).stream().map(
                teacher -> {
                    return teacherMapperr.fromEntity(teacher);
                }).toList();
    }
}
