package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Controller;


import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class ConfigurationGlobalController {

    private final SchoolService schoolService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("school", schoolService.getAll().stream().findFirst().orElse(null));
    }
}
