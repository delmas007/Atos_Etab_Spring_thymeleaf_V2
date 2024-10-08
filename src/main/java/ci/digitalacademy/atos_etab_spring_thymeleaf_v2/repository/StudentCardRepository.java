package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.StudentCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentCardRepository extends JpaRepository<StudentCards, Long> {
    Optional<StudentCards> findBySlug(String slug);
}
