package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.AppSetting;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Teacher;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.AppSettingDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.TeacherDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppSettingMapper extends EntityMapper<AppSettingDTO, AppSetting>  {
}
