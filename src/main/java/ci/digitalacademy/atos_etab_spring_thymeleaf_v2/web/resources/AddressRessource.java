package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.web.resources;


import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AddressService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AddressDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/address")
public class AddressRessource {
    private final AddressService addressService;

    @PostMapping
    @ApiResponse(responseCode = "201", description= "Request to save Address")
    public ResponseEntity<AddressDTO> saveAdress(@RequestBody AddressDTO addressDTO) {
        log.debug("REST request to save Adress: {}", addressDTO);
        return new ResponseEntity<>(addressService.save(addressDTO), HttpStatus.CREATED);
    }
}
