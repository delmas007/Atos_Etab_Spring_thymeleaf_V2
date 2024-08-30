package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.AppSettingDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto.AppSettingDTO;

import java.util.List;
import java.util.Optional;

public interface AppSettingService {
    AppSettingDTO save(AppSettingDTO AppSettingDTO);
    AppSettingDTO update(AppSettingDTO AppSettingDTO);
    void delete(Long id);
    List<AppSettingDTO> getAll();
    Optional<AppSettingDTO> findOne(Long id);

    List<AppSettingDTO>findAllBySmtpUsernames(String smtpUsername);
}
