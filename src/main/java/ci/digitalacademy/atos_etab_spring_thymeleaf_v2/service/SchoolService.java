package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AppSettingDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.SchoolDTO;

import java.util.List;
import java.util.Optional;

public interface SchoolService {
    SchoolDTO save(SchoolDTO SchoolDTO);
    SchoolDTO update(SchoolDTO SchoolDTO);
    void delete(Long id);
    List<SchoolDTO> getAll();
    SchoolDTO initSchool(SchoolDTO schoolDTO);
    SchoolDTO existingParameter();
    Optional<SchoolDTO> findOne(Long id);
}
