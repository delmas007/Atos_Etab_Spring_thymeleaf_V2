package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School,Long> {
}
