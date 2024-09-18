package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTTokenDTO {

    @JsonProperty("id_token")
    private String idToken;
}
