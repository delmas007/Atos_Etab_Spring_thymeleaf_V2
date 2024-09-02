package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.AbsenceRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AbsenceService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.AbsenceMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AbsenceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AbsenceServiceImp implements AbsenceService {
    private final AbsenceRepository absenceRepository;
    private final AbsenceMapper absenceMapper;

    @Override
    public AbsenceDTO save(AbsenceDTO AbsenceDTO) {
        return absenceMapper.fromEntity(absenceRepository.save(absenceMapper.toEntity(AbsenceDTO)));
    }

    @Override
    public AbsenceDTO update(AbsenceDTO AbsenceDTO) {
        return findOne(AbsenceDTO.getAbsenceNumber()).map(existingAbsence -> {
            existingAbsence.setAbsenceDate(AbsenceDTO.getAbsenceDate());
            existingAbsence.setAbsenceDate(AbsenceDTO.getAbsenceDate());
            return save(existingAbsence);
        }).orElseThrow(() -> new RuntimeException("Absence not found"));
    }

    @Override
    public void delete(Long id) {
        absenceRepository.deleteById(id);
    }

    @Override
    public List<AbsenceDTO> getAll() {
        return absenceRepository.findAll().stream().map(Absence -> {
            return absenceMapper.fromEntity(Absence);
        }).toList();
    }

    @Override
    public Optional<AbsenceDTO> findOne(Long id) {
        return absenceRepository.findById(id).map(Absence -> {
            return absenceMapper.fromEntity(Absence);
        });
    }
}
