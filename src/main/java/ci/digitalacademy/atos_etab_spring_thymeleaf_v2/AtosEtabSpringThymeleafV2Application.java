package ci.digitalacademy.atos_etab_spring_thymeleaf_v2;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AppSettingService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.RoleUserService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.SchoolService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.UserService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AppSettingDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.RoleUserDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.SchoolDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class AtosEtabSpringThymeleafV2Application implements CommandLineRunner {
    private final AppSettingService appSettingService;
    private final SchoolService schoolService;
    private final RoleUserService roleUserService;
    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(AtosEtabSpringThymeleafV2Application.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
        AppSettingDTO appSettingDTO = new AppSettingDTO();
        appSettingDTO.setSmtpPassword("password");
        appSettingDTO.setSmtpPort(8080L);
        appSettingDTO.setSmtpUsername("username");
        appSettingDTO.setSmtpServer("host");
        System.out.println(appSettingDTO);
        AppSettingDTO settingDTO = appSettingService.initApp(appSettingDTO);
        SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setName("Ecole de formation");
        schoolDTO.setLogo("https://www.google.com/url?sa=i&url=https%3A%2F%2Fville-saint-gobain.fr%2Fpage-daccueil%2Flogo-ecole-2%2F&psig=AOvVaw2RGcw1mpgHA2w8aO9S6gAE&ust=1725357364930000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCIit0Jr_o4gDFQAAAAAdAAAAABAE");
        schoolDTO.setAppSetting(settingDTO);
        SchoolDTO school = schoolService.initSchool(schoolDTO);

        RoleUserDTO roleUserDTO = new RoleUserDTO();
        RoleUserDTO roleUserDTO2= new RoleUserDTO();
        RoleUserDTO roleUserDTO3= new RoleUserDTO();
        roleUserDTO.setRole("ADMIN");
        roleUserDTO2.setRole("USER");
        roleUserDTO3.setRole("OTHER");
        List<RoleUserDTO> roleUserDTOS = List.of(roleUserDTO, roleUserDTO2, roleUserDTO3);
        roleUserDTOS =roleUserService.initRoles(roleUserDTOS);

        Set<RoleUserDTO> roleUserDTOSet = new HashSet<>();
        roleUserDTOSet.add(roleUserDTOS.get(0));
        Set<RoleUserDTO> roleUserDTOSet2 = new HashSet<>();
        roleUserDTOSet2.add(roleUserDTOS.get(1));
        Set<RoleUserDTO> roleUserDTOSet3 = new HashSet<>();
        roleUserDTOSet3.add(roleUserDTOS.get(2));

        UserDTO userDTO = new UserDTO();
        userDTO.setPseudo("delmas");
        userDTO.setPassword("delmas");
        userDTO.setCreationDate(Date.from(Instant.now()));
        userDTO.setSchool(school);
        userDTO.setRoleUser(roleUserDTOSet);

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setPseudo("delmas2");
        userDTO2.setPassword("delmas2");
        userDTO2.setCreationDate(Date.from(Instant.now()));
        userDTO2.setSchool(school);
        userDTO2.setRoleUser(roleUserDTOSet2);

        UserDTO userDTO3 = new UserDTO();
        userDTO3.setPseudo("delmas3");
        userDTO3.setPassword("delmas3");
        userDTO3.setCreationDate(Date.from(Instant.now()));
        userDTO3.setSchool(school);
        userDTO3.setRoleUser(roleUserDTOSet3);

        userService.initUser(List.of(userDTO, userDTO2, userDTO3));




    }
}
