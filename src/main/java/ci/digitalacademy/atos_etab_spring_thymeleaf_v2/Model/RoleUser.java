package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class RoleUser {
    private Long id;
    private String role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
