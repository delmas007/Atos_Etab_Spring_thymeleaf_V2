package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AppSettingDTO;

import java.util.List;
import java.util.Optional;

public interface AppSettingService {
    AppSettingDTO save(AppSettingDTO AppSettingDTO);
    AppSettingDTO update(AppSettingDTO AppSettingDTO);
    void delete(Long id);
    List<AppSettingDTO> getAll();
    Optional<AppSettingDTO> findOneById(Long id);
    Optional<AppSettingDTO> findOneBySlug(String Slug);
    AppSettingDTO initApp(AppSettingDTO appSettingDTO);
    AppSettingDTO existingParameter();
    List<AppSettingDTO>findAllBySmtpUsernames(String smtpUsername);
}
