package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AppSettingDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.RoleUserDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.SchoolDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.UserDTO;

import java.util.List;
import java.util.Set;

public interface AppService {

    AppSettingDTO initApp(AppSettingDTO appSettingDTO);

    SchoolDTO initSchool(SchoolDTO schoolDTO,AppSettingDTO appSettingDTO);

    List<RoleUserDTO> initRoleUser(List<RoleUserDTO> roleUserDTOs);

    void initUser(List<RoleUserDTO> roleUserDTOs, SchoolDTO schoolDTO, List<UserDTO> userDTOs);
}
