package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.*;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AppSettingDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.RoleUserDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.SchoolDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AppServiceImp implements AppService {
    private final AppSettingService appSettingService;
    private final SchoolService schoolService;
    private final RoleUserService roleUserService;
    private final UserService userService;


    @Override
    public AppSettingDTO initApp(AppSettingDTO appSettingDTO) {
        return appSettingService.initApp(appSettingDTO);
    }

    @Override
    public SchoolDTO initSchool(SchoolDTO schoolDTO, AppSettingDTO appSettingDTO) {
        schoolDTO.setAppSetting(appSettingDTO);
        return schoolService.initSchool(schoolDTO);
    }

    @Override
    public List<RoleUserDTO> initRoleUser(List<RoleUserDTO> roleUserDTOs) {
        return roleUserService.initRoles(roleUserDTOs);
    }

    @Override
    public void initUser(List<RoleUserDTO> roleUserDTOs, SchoolDTO schoolDTO, List<UserDTO> userDTOs) {
        userDTOs.forEach(userDTO -> {
            userDTO.setCreationDate(Date.from(Instant.now()));
            userDTO.setSchool(schoolDTO);
        });
        userService.initUser(userDTOs);

    }

//    public void initUser(List<RoleUserDTO> roleUserDTOs, SchoolDTO schoolDTO, List<UserDTO> userDTOs) {
//        userDTOs.forEach(userDTO -> {
//            userDTO.setCreationDate(Date.from(Instant.now()));
//            userDTO.setSchool(schoolDTO);
//
//            Set<RoleUserDTO> uniqueRoleUserDTOSet = new HashSet<>();
//            roleUserDTOs.forEach(roleUserDTO -> {
//                uniqueRoleUserDTOSet.add(roleUserDTO);
//            });
//            userDTO.setRoleUser(uniqueRoleUserDTOSet);
//        });
//        userService.initUser(userDTOs);
//    }
}
