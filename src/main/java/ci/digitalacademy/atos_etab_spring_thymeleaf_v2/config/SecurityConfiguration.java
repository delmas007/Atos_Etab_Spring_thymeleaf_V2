package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable) // Désactiver la protection CSRF pour cette configuration
                .authorizeHttpRequests((authorize) -> authorize
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/icon/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
//                        .requestMatchers("/connexion/**").permitAll()
                        .requestMatchers("/excels/**").permitAll()
                        .requestMatchers("/dashbord/**").permitAll()
                        .requestMatchers("/reports/**").permitAll()
                        .requestMatchers("/schools/**").permitAll()
                        .requestMatchers("/PostSchool/**").permitAll()
                        .requestMatchers("/settings/**").permitAll()
                        .requestMatchers("/PostSettings/**").permitAll()
                        .requestMatchers("/cards/**").permitAll()
                        .requestMatchers("/students/**").permitAll()
                        .requestMatchers("/professors/**").permitAll()
                        .requestMatchers("/users/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((login) -> login
                        .loginPage("/connexion").permitAll()
                        .defaultSuccessUrl("/dashbord", true)
                        .failureUrl("/login?error=true")
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

}
