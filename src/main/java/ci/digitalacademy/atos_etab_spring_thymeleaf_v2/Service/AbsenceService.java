package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.AbsenceDTO;

import java.util.List;
import java.util.Optional;

public interface AbsenceService {
    AbsenceDTO save(AbsenceDTO AbsenceDTO);
    AbsenceDTO update(AbsenceDTO AbsenceDTO);
    void delete(Long id);
    List<AbsenceDTO> getAll();
    Optional<AbsenceDTO> findOne(Long id);
}
