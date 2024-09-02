package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Controller;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.StudentCards;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AppService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AppSettingService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.SchoolService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AppSettingDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.RoleUserDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.SchoolDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;
    private final AppSettingService appSettingService;
    private final AppService appService;

    @GetMapping("/schools")
    public String showAddCardPage(HttpServletRequest request, Model model){
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("School", new SchoolDTO());
        return "school/forms";
    }

    @PostMapping("/PostSchool")
    public String saveStudent(SchoolDTO schoolDTO){
        AppSettingDTO settingDTO = appSettingService.getAll().stream().findFirst().orElse(null);
        schoolDTO.setAppSetting(settingDTO);
        SchoolDTO save = schoolService.save(schoolDTO);
        createUser(save);
        return "redirect:/";
    }

    public void createUser(SchoolDTO school){
        RoleUserDTO roleUserDTO = new RoleUserDTO();
        RoleUserDTO roleUserDTO2= new RoleUserDTO();
        RoleUserDTO roleUserDTO3= new RoleUserDTO();
        roleUserDTO.setRole("ADMIN");
        roleUserDTO2.setRole("USER");
        roleUserDTO3.setRole("OTHER");
        List<RoleUserDTO> roleUserDTOS = List.of(roleUserDTO, roleUserDTO2, roleUserDTO3);
        roleUserDTOS =appService.initRoleUser(roleUserDTOS);

        Set<RoleUserDTO> roleUserDTOSet = new HashSet<>();
        roleUserDTOSet.add(roleUserDTOS.get(0));
        Set<RoleUserDTO> roleUserDTOSet2 = new HashSet<>();
        roleUserDTOSet2.add(roleUserDTOS.get(1));
        Set<RoleUserDTO> roleUserDTOSet3 = new HashSet<>();
        roleUserDTOSet3.add(roleUserDTOS.get(2));

        UserDTO userDTO = new UserDTO();
        userDTO.setPseudo("delmas");
        userDTO.setPassword("delmas");
        userDTO.setRoleUser(roleUserDTOSet);

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setPseudo("delmas2");
        userDTO2.setPassword("delmas2");
        userDTO2.setRoleUser(roleUserDTOSet2);

        UserDTO userDTO3 = new UserDTO();
        userDTO3.setPseudo("delmas3");
        userDTO3.setPassword("delmas3");
        userDTO3.setRoleUser(roleUserDTOSet3);
        List<UserDTO> listUser = List.of(userDTO, userDTO2, userDTO3);
        appService.initUser(roleUserDTOS,school,listUser);
    }
}
