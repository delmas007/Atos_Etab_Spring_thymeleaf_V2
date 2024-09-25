package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.web.resource;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AddressService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AddressDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressRessourceIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AddressService addressService;

    @Test
    void givenAddressDTO_whenSaveAddress_thenReturnAddressDTO() {
        // Arrange
        AddressDTO address = new AddressDTO(null, "ABIDJAN", "COCODY", "ABIDJAN","CIV");

        // Act
        ResponseEntity<AddressDTO> response = restTemplate.postForEntity("/api/address", address, AddressDTO.class);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("CIV", response.getBody().getCountry());

        // Verify in database
        AddressDTO savedUser = addressService.findOne(response.getBody().getId()).orElse(null);
        assertNotNull(savedUser);
        assertEquals("CIV", savedUser.getCountry());
    }
}
