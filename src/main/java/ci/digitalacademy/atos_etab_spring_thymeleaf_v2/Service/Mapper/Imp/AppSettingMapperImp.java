package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.AppSetting;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.AppSettingMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.AbsenceDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.AppSettingDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppSettingMapperImp implements AppSettingMapper {

    private final ModelMapper modelMapper;

    @Override
    public AppSettingDTO fromEntity(AppSetting entity) {
        return modelMapper.map(entity, AppSettingDTO.class);
    }

    @Override
    public AppSetting toEntity(AppSettingDTO dto) {
        return modelMapper.map(dto, AppSetting.class);
    }
}
