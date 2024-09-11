package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.AbsenceRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AbsenceService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.AbsenceMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.AbsenceMapperr;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AbsenceDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AbsenceServiceImp implements AbsenceService {
    private final AbsenceRepository absenceRepository;
    private final AbsenceMapperr absenceMapper;

    @Override
    public AbsenceDTO save(AbsenceDTO AbsenceDTO) {
        return absenceMapper.fromEntity(absenceRepository.save(absenceMapper.toEntity(AbsenceDTO)));
    }

    @Override
    public AbsenceDTO update(AbsenceDTO AbsenceDTO) {
        return findOne(AbsenceDTO.getId()).map(existingAbsence -> {
            if (AbsenceDTO.getAbsenceDate() != null){
                existingAbsence.setAbsenceDate(AbsenceDTO.getAbsenceDate());
            }
            if (AbsenceDTO.getAbsenceNumber() != null){
                existingAbsence.setAbsenceNumber(AbsenceDTO.getAbsenceNumber());
            }
            if (AbsenceDTO.getStudent() != null){
                existingAbsence.setStudent(AbsenceDTO.getStudent());
            }
            return save(existingAbsence);
        }).orElseThrow(() -> new RuntimeException("Absence not found"));
    }

    @Override
    public AbsenceDTO update(AbsenceDTO absenceDTO, Long id) {
        absenceDTO.setId(id);
        return save(absenceDTO);
    }

    @Override
    public AbsenceDTO updatePartiel(AbsenceDTO absenceDTO, Long id) {
        absenceDTO.setId(id);
        return update(absenceDTO);
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

    @Override
    public AbsenceDTO saveAbsence(AbsenceDTO absence) {
        final String slug = SlugifyUtils.generate(absence.getAbsenceNumber().toString());
        absence.setSlug(slug);
        return save(absence);
    }
}
