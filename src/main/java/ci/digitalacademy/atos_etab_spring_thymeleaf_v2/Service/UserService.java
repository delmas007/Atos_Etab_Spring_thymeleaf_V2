package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO save(UserDTO UserDTO);
    UserDTO update(UserDTO UserDTO);
    void delete(Long id);
    List<UserDTO> getAll();
    Optional<UserDTO> findOne(Long id);
}
