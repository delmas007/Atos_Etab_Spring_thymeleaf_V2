package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Repository;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Absence;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRepository extends JpaRepository<Absence,Long> {
}
