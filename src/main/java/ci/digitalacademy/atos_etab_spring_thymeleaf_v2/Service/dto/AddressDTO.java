package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class AddressDTO {
    private Long id;
    private String city;
    private String street;
    private String country;
}
