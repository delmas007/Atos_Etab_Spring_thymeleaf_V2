package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.RoleUserRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.RoleUserMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.RoleUserService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.RoleUserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleUserServiceImp implements RoleUserService {
    private final RoleUserRepository roleUserRepository;
    private final RoleUserMapper roleUserMapper;
    @Override
    public RoleUserDTO save(RoleUserDTO roleUserDTO) {
        return roleUserMapper.fromEntity(roleUserRepository.save(roleUserMapper.toEntity(roleUserDTO)));
    }

    @Override
    public RoleUserDTO update(RoleUserDTO roleUserDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {
        roleUserRepository.deleteById(id);
    }

    @Override
    public List<RoleUserDTO> getAll() {
        return roleUserRepository.findAll().stream().map(role -> {
            return roleUserMapper.fromEntity(role);
        }).toList();
    }

    @Override
    public Optional<RoleUserDTO> findOne(Long id) {
        return roleUserRepository.findById(id).map(role -> {
            return roleUserMapper.fromEntity(role);
        });
    }

    @Override
    public List<RoleUserDTO> verifyExistingRoles() {
        return List.of();
    }

    @Override
    public List<RoleUserDTO> initRoles(List<RoleUserDTO> rolesUserDTO) {
        log.debug("Request to init roles {}", rolesUserDTO);
        List<RoleUserDTO> roleUserDTOS = getAll();
        if (roleUserDTOS.isEmpty()) {
            rolesUserDTO.forEach(roleUserDTO -> {
                save(roleUserDTO);
            });
        }
        return getAll();
    }

    @Override
    public Set<RoleUserDTO> findByRole(String user) {
        return roleUserRepository.findByRole(user).stream().map(role -> {
            return roleUserMapper.fromEntity(role);
        }).collect(Collectors.toSet());
    }
}
