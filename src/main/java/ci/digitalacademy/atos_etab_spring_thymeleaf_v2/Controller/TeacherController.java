package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Controller;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.TeacherService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.TeacherDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("professors")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/add")
    public String showAddProfessorPage(HttpServletRequest request, Model model){
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("professor", new TeacherDTO());
        return "professors/forms";
    }

    @GetMapping("/update")
    public String showUpdateProfessorPage(HttpServletRequest request, Model model){
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        return "professors/forms";
    }

    @GetMapping
    public String showProfessorPage(HttpServletRequest request, Model model){
        List<TeacherDTO> professors = teacherService.getAll();
        String currentUrl = request.getRequestURI();
        model.addAttribute("professors", professors);
        model.addAttribute("currentUrl", currentUrl);
        return "professors/list";
    }

    @PostMapping
    public String saveProfessor(TeacherDTO professor){
        professor.getUser().setCreationDate(Date.from(Instant.now()));
        teacherService.saveUserAndTeacher(professor);
        return "redirect:/professors";
    }

    @GetMapping("/{id}")
    public String showUpdateProfessorForm(HttpServletRequest request, Model model, @PathVariable Long id){
        String currentUrl = request.getRequestURI();
        Optional<TeacherDTO> professor = teacherService.findOne(id);
        model.addAttribute("currentUrl", currentUrl);
        if(professor.isPresent()){
            model.addAttribute("professor", professor.get());
            return "professors/forms";
        }else {
            return "redirect:/professors";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteProfessor(@PathVariable Long id){
        teacherService.delete(id);
        return "redirect:/professors";
    }

    @GetMapping("/search")
    public String searchTeachers(@RequestParam String query  ,@RequestParam String gender, Model model)
    {
        List<TeacherDTO> teachers = teacherService.findByLastNameOrSpecialtyAndGender(query  , gender);
        model.addAttribute("professors", teachers);
        model.addAttribute("query", query);
        model.addAttribute("gender", gender);

        return "professors/list";
    }

}
