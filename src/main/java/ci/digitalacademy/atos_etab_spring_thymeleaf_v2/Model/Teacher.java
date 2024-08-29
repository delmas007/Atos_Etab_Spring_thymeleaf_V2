package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


public class Teacher extends Person {


    private boolean available;
    private String specialty;
}
