package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.SchoolRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.SchoolMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.SchoolMapperr;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.SchoolService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AppSettingDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.SchoolDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolServiceImp implements SchoolService {

    private final SchoolRepository schoolRepository;
//    private final SchoolMapper schoolMapper;
    private final SchoolMapperr schoolMapper;
    @Override
    public SchoolDTO save(SchoolDTO schoolDTO) {
        return schoolMapper.fromEntity(schoolRepository.save(schoolMapper.toEntity(schoolDTO)));
    }

    @Override
    public SchoolDTO update(SchoolDTO schoolDTO) {
        return findOne(schoolDTO.getId()).map(existingSchool -> {
            existingSchool.setName(schoolDTO.getName());
            return save(existingSchool);
        }).orElseThrow(() -> new RuntimeException("School not found"));
    }

    @Override
    public void delete(Long id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public List<SchoolDTO> getAll() {
        return schoolRepository.findAll().stream().map(school -> {
            return schoolMapper.fromEntity(school);
        }).toList();
    }

    @Override
    public SchoolDTO initSchool(SchoolDTO schoolDTO) {
        SchoolDTO school = existingParameter();
        if (Objects.isNull(school)) {
            return save(schoolDTO);
        }
        return school;
    }

    @Override
    public SchoolDTO existingParameter() {
        List<SchoolDTO> all = getAll();
        return all.stream().findFirst().orElse(null);
    }

    @Override
    public Optional<SchoolDTO> findOne(Long id) {
        return schoolRepository.findById(id).map(address -> {
            return schoolMapper.fromEntity(address);
        });
    }
}
