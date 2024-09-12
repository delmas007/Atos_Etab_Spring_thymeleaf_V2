package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AddressDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AddressService {
    AddressDTO save(AddressDTO addressDTO);
    AddressDTO update(AddressDTO addressDTO);
    void delete(Long id);
    List<AddressDTO> getAll();
    Optional<AddressDTO> findOne(Long id);
}
