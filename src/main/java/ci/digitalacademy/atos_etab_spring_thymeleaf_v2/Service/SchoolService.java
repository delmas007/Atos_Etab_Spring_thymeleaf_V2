package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.SchoolDTO;

import java.util.List;
import java.util.Optional;

public interface SchoolService {
    SchoolDTO save(SchoolDTO SchoolDTO);
    SchoolDTO update(SchoolDTO SchoolDTO);
    void delete(Long id);
    List<SchoolDTO> getAll();
    Optional<SchoolDTO> findOne(Long id);
}
