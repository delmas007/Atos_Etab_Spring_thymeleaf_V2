package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AbsenceDTO;

import java.util.List;
import java.util.Optional;

public interface AbsenceService {
    AbsenceDTO save(AbsenceDTO AbsenceDTO);
    AbsenceDTO update(AbsenceDTO AbsenceDTO);
//    AbsenceDTO updatePartiel(AbsenceDTO AbsenceDTO);
    AbsenceDTO update(AbsenceDTO AbsenceDTO, Long id);
    AbsenceDTO updatePartiel(AbsenceDTO AbsenceDTO, Long id);
    void delete(Long id);
    List<AbsenceDTO> getAll();
    Optional<AbsenceDTO> findOne(Long id);

    AbsenceDTO saveAbsence(AbsenceDTO absence);
}
