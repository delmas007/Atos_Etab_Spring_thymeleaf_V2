package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Controller;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.SchoolService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("login")
@RequiredArgsConstructor
public class AuthController {

    @GetMapping
    public String showLoginPage(Model model, HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/dashbord";
        }
        return "auth/login";
    }
}
