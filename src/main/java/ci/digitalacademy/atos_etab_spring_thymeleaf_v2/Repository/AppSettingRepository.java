package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Repository;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.AppSetting;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppSettingRepository extends JpaRepository<AppSetting,Long> {
}
