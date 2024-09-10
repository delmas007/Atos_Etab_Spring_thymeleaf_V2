package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI getOpenAPI() {
        final Info info = new Info()
                .title("MonEtabAPIs")
                .version("2.0.2")
                .description("Theses APIs exposes Mon ETAB endpoints");
        return new OpenAPI().info(info);
    }
}
