package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper;


import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.User;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {
}
