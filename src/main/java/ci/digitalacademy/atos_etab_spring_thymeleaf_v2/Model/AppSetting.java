package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model;

import jakarta.persistence.OneToOne;

public class AppSetting {
    private Long id;
    private String smtpServer;
    private int smtpPort;
    private String smtpUsername;
    private String smtpPassword;

    @OneToOne
    private School school;
}
