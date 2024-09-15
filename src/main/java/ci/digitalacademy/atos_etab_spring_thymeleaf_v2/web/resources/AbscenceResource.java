package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.web.resources;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AbsenceService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AbsenceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/absences")
public class AbscenceResource {
    private final AbsenceService abscenceService;
    
    @PostMapping
    @ApiResponse(responseCode = "201", description= "Request to save Absence")
    @Operation(summary = "Absence new save", description = "this endpoint allow to save Absence")
    public ResponseEntity<AbsenceDTO> saveAbsence(@RequestBody AbsenceDTO absence){
        log.debug("REST Request to save Absence : {}", absence);
        return new ResponseEntity<>(abscenceService.saveAbsence(absence), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Absence updated")
    @Operation(summary = "Update an existing Absence", description = "Update an existing Absence")
    public ResponseEntity<?> updateAbsence(@RequestBody AbsenceDTO absence,@PathVariable Long id){
        log.debug("REST Request to update Absence : {}", absence);
        return new ResponseEntity<>(abscenceService.update(absence, id), HttpStatus.OK);
    }
    
    @GetMapping
    @Operation(summary = "Get all Absences", description = "Get all Absences")
    public List<AbsenceDTO> getAllAbsence(){
        log.debug("REST Request to get all Absences");
        return abscenceService.getAll();
    }
    
    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Request to get Absence"),
            @ApiResponse(responseCode = "404", description = "Absence not found")
    })
    @Operation(summary = "Get Absence by id", description = "Get Absence by id")
    public ResponseEntity<?> getAbsence(@PathVariable Long id){
        log.debug("REST Request to get Absence : {}", id);
        Optional<AbsenceDTO> one = abscenceService.findOne(id);
        if (one.isPresent()){
            return new ResponseEntity<>(one.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Abscence not found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Absence by id", description = "Delete Absence by id")
    public void deleteAbsence(@PathVariable Long id){
        log.debug("REST Request to delete Absence : {}", id);
        abscenceService.delete(id);
    }

    @PatchMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to partial update Absence"),
            @ApiResponse(responseCode = "404", description = "Absence not found")
    })
    @Operation(summary = "Partial update Absence by id", description = "Partial update Absence by id")
    public ResponseEntity<?> partialUpdateAbsence(@RequestBody AbsenceDTO absence,@PathVariable Long id){
        log.debug("REST Request to partial update Absence : {}", absence);
        Optional<AbsenceDTO> one = abscenceService.findOne(id);
        if (one.isPresent()){
            return new ResponseEntity<>(abscenceService.updatePartiel(absence, id), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Abscence not found",HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Request to get Absence by slug"),
            @ApiResponse(responseCode = "404", description = "Absence not found")
    })
    @Operation(summary = "Get Absence by slug", description = "Get Absence by slug")
    public ResponseEntity<?> getOneBySlug(@PathVariable  String slug){
        log.debug("REST Request to get Absence by slug : {}", slug);
        return null;
    }
    
    
}
