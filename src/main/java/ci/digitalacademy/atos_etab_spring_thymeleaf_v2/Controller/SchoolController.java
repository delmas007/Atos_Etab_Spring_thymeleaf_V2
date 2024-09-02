package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Controller;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.StudentCards;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.SchoolService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.SchoolDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;
    @GetMapping("/schools")
    public String showAddCardPage(HttpServletRequest request, Model model){
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("School", new SchoolDTO());
        return "school/forms";
    }
}
