package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Controller;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AppService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AppSettingService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.SchoolService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AppSettingDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.RoleUserDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.SchoolDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class IndexController {
    private final AppSettingService appSettingService;
    private final SchoolService schoolService;


    @GetMapping
    public  String verifyConfiguration() {
//        AppSettingDTO appSettingDTO = new AppSettingDTO();
//        appSettingDTO.setSmtpPassword("password");
//        appSettingDTO.setSmtpPort(8080L);
//        appSettingDTO.setSmtpUsername("username");
//        appSettingDTO.setSmtpServer("host");
//        System.out.println(appSettingDTO);
//        AppSettingDTO settingDTO = appService.initApp(appSettingDTO);
//
//        SchoolDTO schoolDTO = new SchoolDTO();
//        schoolDTO.setName("Ecole de formation");
//        schoolDTO.setLogo("https://www.google.com/url?sa=i&url=https%3A%2F%2Fville-saint-gobain.fr%2Fpage-daccueil%2Flogo-ecole-2%2F&psig=AOvVaw2RGcw1mpgHA2w8aO9S6gAE&ust=1725357364930000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCIit0Jr_o4gDFQAAAAAdAAAAABAE");
//        SchoolDTO school = appService.initSchool(schoolDTO,settingDTO);




        List<AppSettingDTO> appSettingDTOS = appSettingService.getAll();
        List<SchoolDTO> schoolDTOS = schoolService.getAll();
        if (appSettingDTOS.isEmpty()) {
            return "redirect:/settings";
        } else if (schoolDTOS.isEmpty()) {
            return "redirect:/schools";
        }else {
            return "redirect:/connexion";
        }
    }

}

