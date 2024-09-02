package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.AppSetting;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.AppSettingMapperr;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AppSettingDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppSettingMapperImp implements AppSettingMapperr {

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
