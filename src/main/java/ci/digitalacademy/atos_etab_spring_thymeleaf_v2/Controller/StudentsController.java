package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Controller;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.AddressRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.StudentService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentsController {

    private final StudentService studentService;

    private final AddressRepository addressRepository;

    @GetMapping("/add")
    public String showAddStudentPage(HttpServletRequest request, Model model){
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("student", new StudentDTO());
        return "students/forms";
    }

    @GetMapping("/update")
    public String showUpdateStudentPage(HttpServletRequest request, Model model){
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        return "students/forms";
    }

    @GetMapping
    public String showtudentPage(HttpServletRequest request, Model model){
        String currentUrl = request.getRequestURI();
        List<StudentDTO> students = studentService.getAll();
        model.addAttribute("students", students);
        model.addAttribute("currentUrl", currentUrl);
        return "students/list";
    }

    @PostMapping
    public String saveStudent(StudentDTO student){
        StudentDTO save = studentService.save(student);
        System.out.println("save = " + save);
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String showUpdateProfessorForm(HttpServletRequest request, Model model, @PathVariable Long id){
        String currentUrl = request.getRequestURI();
        Optional<StudentDTO> student = studentService.findOne(id);
        model.addAttribute("currentUrl", currentUrl);
        if(student.isPresent()){
            model.addAttribute("student", student.get());
            return "students/forms";
        }else {
            return "redirect:/students";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.delete(id);
        return "redirect:/students";
    }

    @GetMapping("/search")
    public String searchStudent(@RequestParam String query,@RequestParam String gender,Model model){
        List<StudentDTO> studentDTOS =studentService.findByLastNameOrGenderOrMatricule(query,gender);
        model.addAttribute("students",studentDTOS);
        return "students/list";
    }

}
