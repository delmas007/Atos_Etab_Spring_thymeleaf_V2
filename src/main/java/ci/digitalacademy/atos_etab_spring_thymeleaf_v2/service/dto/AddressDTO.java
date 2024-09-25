package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import lombok.*;

@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
public class AddressDTO {
    private Long id;
    private String slug;
    private String city;
    private String street;
    private String country;
}
