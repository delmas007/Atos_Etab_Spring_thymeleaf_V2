package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.AppSettingRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AppSettingService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.AppSettingMapper;
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
    private final AppSettingMapper appSettingMapper;
    @Override
    public AppSettingDTO save(AppSettingDTO appSettingDTO) {
        return appSettingMapper.fromEntity(appSettingRepository.save(appSettingMapper.toEntity(appSettingDTO)));
    }

    @Override
    public AppSettingDTO update(AppSettingDTO appSettingDTO) {
        return findOne(appSettingDTO.getId()).map(existingAppSetting -> {
            existingAppSetting.setSmtp_Password(appSettingDTO.getSmtp_Password());
            existingAppSetting.setSmtp_Port(appSettingDTO.getSmtp_Port());
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
    public List<AppSettingDTO> findAllBySmtpUsernames(String smtpUsername) {
        log.debug("Request to get all AppSetting by smtpUsername : {}", smtpUsername);
        return appSettingRepository.findBySmtpUsername(smtpUsername).stream().map(appSetting -> {
            return appSettingMapper.fromEntity(appSetting);
        }).toList();
    }
}
