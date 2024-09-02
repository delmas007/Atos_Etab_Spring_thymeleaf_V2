package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.User;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.UserMapperr;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapperImp implements UserMapperr {
    private final ModelMapper modelMapper;
    @Override
    public UserDTO fromEntity(User entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    @Override
    public User toEntity(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }
}
