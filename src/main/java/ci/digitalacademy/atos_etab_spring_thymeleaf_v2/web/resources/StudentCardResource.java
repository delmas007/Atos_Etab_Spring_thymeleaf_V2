package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.web.resources;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.StudentCardService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentCardDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/studentCards")
public class StudentCardResource {
    private final StudentCardService studentCardService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Request to save studentCard")
    @Operation(summary = "studentCard new save", description = "this endpoint allow to save studentCard")
    public ResponseEntity<StudentCardDTO> saveStudentCard(StudentCardDTO studentCardDTO){
        log.debug("REST Request to save StudentCard");
        return new ResponseEntity<>(studentCardService.save(studentCardDTO), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiResponse(responseCode = "200", description = "StudentCard updated")
    @Operation(summary = "Update an existing StudentCard", description = "Update an existing StudentCard")
    public ResponseEntity<StudentCardDTO> updateStudentCard(StudentCardDTO studentCardDTO){
        log.debug("REST Request to update StudentCard");
        return new ResponseEntity<>(studentCardService.update(studentCardDTO), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all StudentCard", description = "Get all StudentCard")
    public ResponseEntity<?> getAllStudentCard(){
        log.debug("REST Request to get all StudentCard");
        return new ResponseEntity<>(studentCardService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Request to get StudentCard"),
            @ApiResponse(responseCode = "404", description = "StudentCard not found")
    })
    public ResponseEntity<?> getOneStudentCard(@PathVariable Long id){
        log.debug("REST Request to get one StudentCard : {}", id);
        Optional<StudentCardDTO> one = studentCardService.findOne(id);
        if (one.isPresent()){
            return new ResponseEntity<>(one.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("StudentCard not found", HttpStatus.NOT_FOUND);
        }
    }
}
