package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AppService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AppSettingDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.RoleUserDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.SchoolDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.UserDTO;

import java.util.List;

public class AppServiceImp implements AppService {
    @Override
    public AppSettingDTO initApp(AppSettingDTO appSettingDTO) {
        return null;
    }

    @Override
    public SchoolDTO initSchool(SchoolDTO schoolDTO, AppSettingDTO appSettingDTO) {
        return null;
    }

    @Override
    public List<RoleUserDTO> initRoleUser(List<RoleUserDTO> roleUserDTOs) {
        return List.of();
    }

    @Override
    public void initUser(List<RoleUserDTO> roleUserDTOs, SchoolDTO schoolDTO, List<UserDTO> userDTOs) {

    }
}
