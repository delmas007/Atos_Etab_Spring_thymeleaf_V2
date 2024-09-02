package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Controller;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.StudentCards;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.StudentCardService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.StudentService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentCardDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("cards")
@RequiredArgsConstructor
public class StudentsCardController {

    private final StudentService studentService;
    private final StudentCardService studentCardService;

    @GetMapping("/add")
    public String showAddCardPage(HttpServletRequest request, Model model){
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("card", new StudentCards());
        model.addAttribute("students", studentService.getAll());
        return "card/forms";
    }

    @GetMapping("/update")
    public String showUpdateCardPage(HttpServletRequest request, Model model){
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        return "card/forms";
    }

    @GetMapping
    public String showCardPage(HttpServletRequest request, Model model){
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("studentCards", studentCardService.getAll());
        return "card/list";
    }

    @PostMapping
    public String saveCard(StudentCardDTO studentCardDTO){
        studentCardDTO.setReference(UUID.randomUUID().toString());
        studentCardDTO.setIssueDate(LocalDate.now());
        studentCardService.save(studentCardDTO);
        return "redirect:/cards";
    }

    @GetMapping("/{id}")
    public String showUpdateCardForm(HttpServletRequest request, Model model, @PathVariable Long id){
        String currentUrl = request.getRequestURI();
        Optional<StudentCardDTO> user = studentCardService.findOne(id);
        model.addAttribute("currentUrl", currentUrl);
        if(user.isPresent()){
            model.addAttribute("user", user.get());
            return "card/forms";
        }else {
            return "redirect:/users";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteCard(@PathVariable Long id){
        studentCardService.delete(id);
        return "redirect:/cards";
    }

}
