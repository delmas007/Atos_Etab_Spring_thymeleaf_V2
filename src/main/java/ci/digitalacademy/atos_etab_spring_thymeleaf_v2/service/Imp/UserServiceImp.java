package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.User;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.UserRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.UserMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.SchoolService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.UserService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.UserDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SchoolService schoolService;
    @Override
    public UserDTO save(UserDTO userDTO) {
        schoolService.getAll().stream().findFirst().ifPresent(schoolDTO -> {
            userDTO.setSchool(schoolDTO);
        });
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userDTO.setSlug(SlugifyUtils.generate(userDTO.getPseudo()));
        return userMapper.fromEntity(userRepository.save(userMapper.toEntity(userDTO)));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO update(UserDTO userDTO, Long id) {
        userDTO.setId(id);
        return save(userDTO);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(user -> {
            return userMapper.fromEntity(user);
        }).toList();
    }

    @Override
    public Optional<UserDTO> findOneById(Long id) {
        return userRepository.findById(id).map(user -> userMapper.fromEntity(user));
    }

    @Override
    public Optional<UserDTO> findOneBySlug(String slug) {
        return userRepository.findBySlug(slug).map(user -> userMapper.fromEntity(user));
    }

    @Override
    public List<UserDTO> initUser(List<UserDTO> UserDTO) {
        List<UserDTO> userDTOS = getAll();
        if (userDTOS.isEmpty()) {
            UserDTO.forEach(userDTO -> {
                save(userDTO);
            });
        }
        return getAll();
    }

    @Override
    public List<UserDTO> findByCreatedDateLessThanAndRoleUserNameRole(Instant createdDate, String role) {
        List<User> users = userRepository.findByCreationDateLessThanAndRoleUserRole(Date.from(createdDate), role);
        return users.stream().map(user -> userMapper.fromEntity(user)).toList();
    }
}
