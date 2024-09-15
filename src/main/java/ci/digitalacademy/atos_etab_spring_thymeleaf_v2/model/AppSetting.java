package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "app_setting")
@Entity
public class AppSetting implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String slug;
    @Column(name = "smtp_server")
    private String smtpServer;

    @Column(name = "smtp_port")
    private Long smtpPort;

    @Column(name = "smtp_username")
    private String smtpUsername;

    @Column(name = "smtp_password")
    private String smtpPassword;

    @OneToOne(mappedBy = "appSetting")
    private School school;
}
