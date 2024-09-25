package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.imp;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Address;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.repository.AddressRepository;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AddressService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp.AddressServiceImp;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AddressDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AddressServiceIntegrationTest {

    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @Test
    void whenFindById_thenReturnAddressNotEmpty() {

        when(addressRepository.findById(1L)).thenReturn(Optional.of(new Address(1L, "ABIDJAN", "COCODY", "CIV","")));

        Optional<AddressDTO> address = addressService.findOne(1L);

        assertTrue(address.isPresent(), "Address is empty");
    }
}
