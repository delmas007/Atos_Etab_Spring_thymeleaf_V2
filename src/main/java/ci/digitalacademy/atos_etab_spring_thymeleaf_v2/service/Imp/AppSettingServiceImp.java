package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.AppSettingRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AppSettingService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.AppSettingMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.AppSettingMapperr;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AppSettingDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.utils.SlugifyUtils;
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
        log.debug("Request to save AppSetting : {}", appSettingDTO);
        AppSettingDTO AppSettingDTOfirst = getAll().stream().findFirst().orElse(null);
        if (AppSettingDTOfirst != null) {
            return null;
        }
        appSettingDTO.setSlug(SlugifyUtils.generate(appSettingDTO.getSmtpUsername()));
        return appSettingMapper.fromEntity(appSettingRepository.save(appSettingMapper.toEntity(appSettingDTO)));
    }

    @Override
    public AppSettingDTO update(AppSettingDTO appSettingDTO) {
        log.debug("Request to update AppSetting : {}", appSettingDTO);
        return findOneById(appSettingDTO.getId()).map(existingAppSetting -> {
            if (appSettingDTO.getSmtpUsername() != null) {
                existingAppSetting.setSmtpUsername(appSettingDTO.getSmtpUsername());
            }
            if (appSettingDTO.getSmtpPassword() != null) {
                existingAppSetting.setSmtpPassword(appSettingDTO.getSmtpPassword());
            }
            if (appSettingDTO.getSmtpServer() != null) {
                existingAppSetting.setSmtpServer(appSettingDTO.getSmtpServer());
            }
            if (appSettingDTO.getSmtpPort() != null) {
                existingAppSetting.setSmtpPort(appSettingDTO.getSmtpPort());
            }
            return save(existingAppSetting);
        }).orElseThrow(() -> new RuntimeException("AppSetting not found"));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AppSetting : {}", id);
        appSettingRepository.deleteById(id);
    }

    @Override
    public List<AppSettingDTO> getAll() {
        log.debug("Request to get all AppSetting");
        return appSettingRepository.findAll().stream().map(appSetting -> {
            return appSettingMapper.fromEntity(appSetting);
        }).toList();
    }

    @Override
    public Optional<AppSettingDTO> findOneById(Long id) {
        return appSettingRepository.findById(id).map(address -> {
            return appSettingMapper.fromEntity(address);
        });
    }

    @Override
    public Optional<AppSettingDTO> findOneBySlug(String Slug) {
        return appSettingRepository.findBySlug(Slug).map(address -> {
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
