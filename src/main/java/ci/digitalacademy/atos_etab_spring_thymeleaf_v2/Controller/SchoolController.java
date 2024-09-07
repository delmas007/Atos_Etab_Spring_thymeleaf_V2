package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Controller;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.StudentCards;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AppService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AppSettingService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.FileStorageService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.SchoolService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    private final FileStorageService fileStorageService;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/schools")
    public String showAddSchoolPage(HttpServletRequest request, Model model){
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("school", new RegistrationSchoolDto());
        return "school/forms";
    }


//    @PostMapping("/postschool")
//    public String saveStudent(SchoolDTO schoolDTO){
//        AppSettingDTO settingDTO = appSettingService.getAll().stream().findFirst().orElse(null);
//        schoolDTO.setAppSetting(settingDTO);
//        SchoolDTO save = schoolService.save(schoolDTO);
//        createUserAndRole(save);
//        return "redirect:/";
//    }


    @PostMapping("/postschool")
    public String saveSchool(@ModelAttribute RegistrationSchoolDto registrationSchoolDto) throws IOException {
        String upload = fileStorageService.upload(registrationSchoolDto.getFile());
        AppSettingDTO settingDTO = appSettingService.getAll().stream().findFirst().orElse(null);
        registrationSchoolDto.setAppSetting(settingDTO);
        registrationSchoolDto.setLogo(upload);
        SchoolDTO save = schoolService.save(registrationSchoolDto);
        createUserAndRole(save);
        return "redirect:/";
    }

    public void createUserAndRole(SchoolDTO school){
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
        userDTO.setPassword(bCryptPasswordEncoder.encode("delmas"));
        userDTO.setRoleUser(roleUserDTOSet);

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setPseudo("delmas2");
        userDTO2.setPassword(bCryptPasswordEncoder.encode("delmas2"));
        userDTO2.setRoleUser(roleUserDTOSet2);

        UserDTO userDTO3 = new UserDTO();
        userDTO3.setPseudo("delmas3");
        userDTO3.setPassword(bCryptPasswordEncoder.encode("delmas3"));
        userDTO3.setRoleUser(roleUserDTOSet3);
        List<UserDTO> listUser = List.of(userDTO, userDTO2, userDTO3);
        appService.initUser(roleUserDTOS,school,listUser);
    }
}
