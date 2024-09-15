package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.AppSetting;
import org.apache.commons.lang3.concurrent.UncheckedFuture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppSettingRepository extends JpaRepository<AppSetting,Long> {
   List<AppSetting> findBySmtpUsername(String smtpUsername);

    Optional<AppSetting> findBySlug(String slug);
}
