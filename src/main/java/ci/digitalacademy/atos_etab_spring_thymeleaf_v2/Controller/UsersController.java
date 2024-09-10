package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Controller;


import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.RoleUser;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.User;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.RoleUserRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.UserRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.RoleUserMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.RoleUserService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.UserService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.RegistrationUserDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.RoleUserDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Controller
@RequestMapping("users")
public class UsersController {

    private final UserService userService;
    private final RoleUserService roleUserService;

    @GetMapping("/add")
    public String showAddUserPage(HttpServletRequest request, Model model){
        List<RoleUserDTO> all = roleUserService.getAll();
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("user", new RegistrationUserDTO());
        model.addAttribute("roles", all);
        return "user/forms";
    }

    @GetMapping("/update")
    public String showUpdateUserPage(HttpServletRequest request, Model model){
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        return "user/forms";
    }

    @GetMapping
    public String showUserPage(HttpServletRequest request, Model model){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<UserDTO> users = userService.getAll();
        List<RoleUserDTO> all = roleUserService.getAll();
        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("users", users);
        model.addAttribute("all", all);
        model.addAttribute("namee", name);
        return "user/list";
    }

    @PostMapping("/status/{id}")
    public String toggleUserStatus(@PathVariable Long id) {
        Optional<UserDTO> userDTO = userService.findOne(id);
        UserDTO userDTO1 = userDTO.get();
        userDTO1.setActive(!userDTO1.getActive());
        userService.save(userDTO1);
        return "redirect:/users";
    }


    @PostMapping
    public String saveUser(RegistrationUserDTO user){
        user.setCreationDate(Date.from(Instant.now()));
//        List<RoleUserDTO> all =roleUserService.getAll();
//        RoleUserDTO roleUserDTO1 = all.get(1);
//        Set<RoleUserDTO> roleUserDTOStream = Set.of(roleUserDTO1);
//        user.setRoleUser(roleUserDTOStream);
        Set<RoleUserDTO> roles = new HashSet<>();
        roles.add(roleUserService.findOne(user.getRole()).orElse(null));
        user.setRoleUser(roles);
        user.setActive(true);
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String showUpdateUserForm(HttpServletRequest request, Model model, @PathVariable Long id){
        List<RoleUserDTO> all = roleUserService.getAll();
        String currentUrl = request.getRequestURI();
        Optional<UserDTO> user = userService.findOne(id);
        RegistrationUserDTO userDTO = new RegistrationUserDTO();
        userDTO.setId(user.get().getId());
        userDTO.setPseudo(user.get().getPseudo());
        userDTO.setCreationDate(user.get().getCreationDate());
        userDTO.setActive(user.get().getActive());
        userDTO.setRole(user.get().getRoleUser().stream().findFirst().get().getId());
        model.addAttribute("roles", all);
//        roleUserService.findOne(user.get().getRoleUser().stream().findFirst().get().getId());
        model.addAttribute("currentUrl", currentUrl);
        if(user.isPresent()){
            model.addAttribute("user", userDTO);
            return "user/forms";
        }else {
            return "redirect:/users";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/search")
    public String searchTeachers(@RequestParam LocalDate date  , @RequestParam String role, Model model)
    {
        List<UserDTO> users = userService.findByCreatedDateLessThanAndRoleUserNameRole(Instant.from(date.atStartOfDay(ZoneOffset.systemDefault())), role);
        model.addAttribute("users", users);
        model.addAttribute("date", date);
        model.addAttribute("role", role);
        model.addAttribute("all", roleUserService.getAll());

        return "user/list";
    }

}
