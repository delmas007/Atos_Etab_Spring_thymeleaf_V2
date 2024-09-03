package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
    String upload(MultipartFile file) throws IOException;
}
