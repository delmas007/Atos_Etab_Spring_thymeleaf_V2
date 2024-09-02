package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.RoleUser;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.RoleUserMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.RoleUserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleUserMapperImp implements RoleUserMapper {

    private final ModelMapper modelMapper;

    @Override
    public RoleUserDTO fromEntity(RoleUser entity) {
        return modelMapper.map(entity, RoleUserDTO.class);
    }

    @Override
    public RoleUser toEntity(RoleUserDTO dto) {
        return modelMapper.map(dto, RoleUser.class);
    }
}
