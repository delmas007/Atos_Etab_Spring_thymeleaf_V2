package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Repository;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.School;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School,Long> {
}
