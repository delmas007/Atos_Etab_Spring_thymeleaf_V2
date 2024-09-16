package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.web.resources;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.SchoolService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.SchoolDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/schools")
public class SchoolResource {
    private final SchoolService schoolService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Request to save school")
    @Operation(summary = "school new save", description = "this endpoint allow to save school")
    public ResponseEntity<?> saveSchool(@RequestPart(name = "school") SchoolDTO schoolDTO,@RequestPart(name = "file") MultipartFile file) throws IOException {
        log.debug("REST Request to save school : {}", schoolDTO);
        SchoolDTO save = schoolService.save(schoolDTO,file);
        if (save != null){
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("AppSetting not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    @ApiResponse(responseCode = "200", description = "School updated")
    @Operation(summary = "Update an existing school", description = "Update an existing school")
    public ResponseEntity<SchoolDTO> updateSchool(@RequestBody SchoolDTO schoolDTO){
        log.debug("REST Request to update school : {}", schoolDTO);
        return new ResponseEntity<>(schoolService.update(schoolDTO), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all schools", description = "Get all schools")
    public ResponseEntity<?> getAllSchool(){
        log.debug("REST Request to get all schools");
        return new ResponseEntity<>(schoolService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Request to get school"),
            @ApiResponse(responseCode = "404", description = "school not found")
    })
    @Operation(summary = "Get school by id", description = "Get school by id")
    public ResponseEntity<?> getOneSchool(@PathVariable Long id){
        log.debug("REST Request to get school : {}", id);
        Optional<SchoolDTO> one = schoolService.findOne(id);
        if (one.isPresent()){
            return new ResponseEntity<>(one.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("school not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "School deleted")
    @Operation(summary = "Delete school by id", description = "Delete school by id")
    public void deleteSchool(@PathVariable Long id){
        log.debug("REST Request to delete school : {}", id);
        schoolService.delete(id);
    }
}
