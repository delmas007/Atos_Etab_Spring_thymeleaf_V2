package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model;

import jakarta.persistence.*;
import lombok.*;


public class Address {
    private Long id;
    private String city;
    private String street;
    private String country;
}
