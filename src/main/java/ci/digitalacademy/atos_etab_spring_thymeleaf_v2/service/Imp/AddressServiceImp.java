package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.AddressRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AddressService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Mapper.AddressMapper;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AddressDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImp implements AddressService {
    private  final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    @Override
    public AddressDTO save(AddressDTO addressDTO) {
        return addressMapper.fromEntity(addressRepository.save(addressMapper.toEntity(addressDTO)));
    }

    @Override
    public AddressDTO update(AddressDTO addressDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public List<AddressDTO> getAll() {
        return addressRepository.findAll().stream().map(address -> {
            return addressMapper.fromEntity(address);
        }).toList();
    }

    @Override
    public Optional<AddressDTO> findOne(Long id) {
        return Optional.empty();
    }
}
