package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Repository.AppSettingRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.AppSettingService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.Mapper.AppSettingMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.AppSettingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
            existingAppSetting.setSmtpPort(appSettingDTO.getSmtpPort());
            existingAppSetting.setSmtpServer(appSettingDTO.getSmtpServer());
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
}
