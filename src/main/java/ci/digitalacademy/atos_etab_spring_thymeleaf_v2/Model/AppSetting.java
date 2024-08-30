package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model;

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

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "smtp_server")
    private String smtp_Server;

    @Column(name = "smtp_port")
    private int smtp_Port;

    @Column(name = "smtp_username")
    private String smtp_Username;

    @Column(name = "smtp_password")
    private String smtp_Password;

    @OneToOne(mappedBy = "appSetting")
    private School school;
}
