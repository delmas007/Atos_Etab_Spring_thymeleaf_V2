package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.UserDTO;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO save(UserDTO UserDTO);
    UserDTO update(UserDTO UserDTO);
    UserDTO update(UserDTO UserDTO,Long id);
    void delete(Long id);
    List<UserDTO> getAll();
    Optional<UserDTO> findOneById(Long id);
    Optional<UserDTO> findOneBySlug(String id);
    List<UserDTO> initUser(List<UserDTO> UserDTO);
    List<UserDTO> findByCreatedDateLessThanAndRoleUserNameRole(Instant createdDate, String role);
}
