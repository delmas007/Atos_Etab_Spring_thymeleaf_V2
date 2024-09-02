package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.AppSettingRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AppSettingService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.AppSettingMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.AppSettingMapperr;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AppSettingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppSettingServiceImp implements AppSettingService {
    private final AppSettingRepository appSettingRepository;
//    private final AppSettingMapper appSettingMapper;
    private final AppSettingMapperr appSettingMapper;
    @Override
    public AppSettingDTO save(AppSettingDTO appSettingDTO) {
        return appSettingMapper.fromEntity(appSettingRepository.save(appSettingMapper.toEntity(appSettingDTO)));
    }

    @Override
    public AppSettingDTO update(AppSettingDTO appSettingDTO) {
        return findOne(appSettingDTO.getId()).map(existingAppSetting -> {
            existingAppSetting.setSmtpPassword(appSettingDTO.getSmtpPassword());
            existingAppSetting.setSmtpPort(appSettingDTO.getSmtpPort());
            return save(existingAppSetting);
        }).orElseThrow(() -> new RuntimeException("AppSetting not found"));
    }

    @Override
    public void delete(Long id) {
        appSettingRepository.deleteById(id);
    }

    @Override
    public List<AppSettingDTO> getAll() {
        return appSettingRepository.findAll().stream().map(appSetting -> {
            return appSettingMapper.fromEntity(appSetting);
        }).toList();
    }

    @Override
    public Optional<AppSettingDTO> findOne(Long id) {
        return appSettingRepository.findById(id).map(address -> {
            return appSettingMapper.fromEntity(address);
        });
    }

    @Override
    public AppSettingDTO initApp(AppSettingDTO appSettingDTO) {
        log.debug("Request to init app {}", appSettingDTO);
        System.out.println(appSettingDTO);
        AppSettingDTO settingDTO = existingParameter();
        if (settingDTO == null) {

            return save(appSettingDTO);
        }
        return settingDTO;
    }

    @Override
    public AppSettingDTO existingParameter() {
        log.debug("Request to check if existing parameter");
        List<AppSettingDTO> all = getAll();
        return all.stream().findFirst().orElse(null);
    }

    @Override
    public List<AppSettingDTO> findAllBySmtpUsernames(String smtpUsername) {
        log.debug("Request to get all AppSetting by smtpUsername : {}", smtpUsername);
        return appSettingRepository.findBySmtpUsername(smtpUsername).stream().map(appSetting -> {
            return appSettingMapper.fromEntity(appSetting);
        }).toList();
    }
}
