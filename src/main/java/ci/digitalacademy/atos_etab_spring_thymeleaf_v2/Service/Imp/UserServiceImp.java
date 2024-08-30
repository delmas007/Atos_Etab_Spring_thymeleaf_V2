package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.UserService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public class UserServiceImp implements UserService {
    @Override
    public UserDTO save(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<UserDTO> getAll() {
        return List.of();
    }

    @Override
    public Optional<UserDTO> findOne(Long id) {
        return Optional.empty();
    }
}
