package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public class School {
    private Long id;
    private String name;
    private String logo;

    @OneToMany
    private User user;

    @OneToOne(mappedBy = "school")
    private AppSetting appSetting;
}
