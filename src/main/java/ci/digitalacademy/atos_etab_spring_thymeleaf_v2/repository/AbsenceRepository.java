package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRepository extends JpaRepository<Absence,Long> {
}
