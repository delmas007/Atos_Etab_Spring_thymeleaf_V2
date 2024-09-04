package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByPseudo(String pseudo);
//    List<User> findByCreatedDateLessThanAndRoleUserNameRole(Instant createdDate, String role);
//    List<User> findByCreationDateLessThanAndRoleUserRole(Instant createdDate, String role);
    List<User> findByCreationDateLessThanAndRoleUserRole(Date creationDate, String roleUser_role);
}
