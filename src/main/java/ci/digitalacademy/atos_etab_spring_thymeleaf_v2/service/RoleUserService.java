package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.RoleUser;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AbsenceDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.RoleUserDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleUserService {
    RoleUserDTO save(RoleUserDTO roleUserDTO);
    RoleUserDTO update(RoleUserDTO roleUserDTO);
    void delete(Long id);
    List<RoleUserDTO> getAll();
    Optional<RoleUserDTO> findOne(Long id);
    List<RoleUserDTO> verifyExistingRoles();
    List<RoleUserDTO> initRoles(List<RoleUserDTO> roleUserDTO);

    Set<RoleUserDTO> findByRole(String user);
}
