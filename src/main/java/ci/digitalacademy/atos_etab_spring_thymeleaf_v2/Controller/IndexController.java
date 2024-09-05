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
        List<AppSettingDTO> appSettingDTOS = appSettingService.getAll();
        List<SchoolDTO> schoolDTOS = schoolService.getAll();

        if (appSettingDTOS.isEmpty()) {
            return "redirect:/settings";
        } else if (schoolDTOS.isEmpty()) {
            return "redirect:/schools";
        }else {
            return "redirect:/login";
        }
    }

}

