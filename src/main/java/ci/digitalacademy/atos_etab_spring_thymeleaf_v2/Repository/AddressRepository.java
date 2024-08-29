package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Repository;


import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
