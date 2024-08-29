package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.Imp;

import ci.digitalacademy.monetab.Model.User;
import ci.digitalacademy.monetab.Service.Mapper.UserMapper;
import ci.digitalacademy.monetab.Service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImp implements UserMapper {

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
