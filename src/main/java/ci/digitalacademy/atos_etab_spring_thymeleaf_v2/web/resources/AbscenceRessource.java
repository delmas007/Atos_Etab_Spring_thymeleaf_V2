package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.web.resources;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AbsenceService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AbsenceDTO;
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
public class AbscenceRessource {
    private final AbsenceService abscenceService;
    
    @PostMapping
    public ResponseEntity<AbsenceDTO> saveAbsence(@RequestBody AbsenceDTO absence){
        log.debug("REST Request to save Absence : {}", absence);
        return new ResponseEntity<>(abscenceService.saveAbsence(absence), HttpStatus.CREATED);
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<?> updateAbsence(@RequestBody AbsenceDTO absence,@PathVariable Long id){
        log.debug("REST Request to update Absence : {}", absence);
        return new ResponseEntity<>(abscenceService.update(absence, id), HttpStatus.OK);
    }
    
    @GetMapping
    public List<AbsenceDTO> getAllAbsence(){
        log.debug("REST Request to get all Absences");
        return abscenceService.getAll();
    }
    
    @GetMapping("/{id}")
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
    public void deleteAbsence(@PathVariable Long id){
        log.debug("REST Request to delete Absence : {}", id);
        abscenceService.delete(id);
    }

    @PatchMapping("/{id}")
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
    public ResponseEntity<?> getOneBySlug(@PathVariable  String slug){
        log.debug("REST Request to get Absence by slug : {}", slug);
        return null;
    }
    
    
}
