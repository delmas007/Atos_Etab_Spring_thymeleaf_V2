package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.AppSetting;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AppSettingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppSettingMapper extends EntityMapper<AppSettingDTO, AppSetting>  {
}