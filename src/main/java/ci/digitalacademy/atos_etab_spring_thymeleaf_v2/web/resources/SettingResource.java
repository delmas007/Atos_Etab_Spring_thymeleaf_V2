package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.web.resources;


import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.AppSettingService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AppSettingDTO;
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
@RequestMapping("/api/settings")
public class SettingResource {
    private final AppSettingService settingService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Request to save setting")
    @Operation(summary = "setting new save", description = "this endpoint allow to save setting")
    public ResponseEntity<?>saveSetting(@RequestBody AppSettingDTO appSettingDTO){
        return new ResponseEntity<>(settingService.save(appSettingDTO), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiResponse(responseCode = "200", description = "setting updated")
    @Operation(summary = "Update an existing setting", description = "Update an existing setting")
    public ResponseEntity<?>updateSetting(@RequestBody AppSettingDTO appSettingDTO){
        return new ResponseEntity<>(settingService.update(appSettingDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "setting deleted")
    @Operation(summary = "Delete an existing setting", description = "Delete an existing setting")
    public void deleteSetting(@PathVariable Long id){
        settingService.delete(id);
    }

    @GetMapping
    @Operation(summary = "Get all settings", description = "Get all settings")
    public ResponseEntity<?>getAllSetting(){
        return new ResponseEntity<>(settingService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Request to get setting"),
            @ApiResponse(responseCode = "404", description = "setting not found")
    })
    @Operation(summary = "Get setting by id", description = "Get setting by id")
    public ResponseEntity<?>getOneSetting(@PathVariable Long id){
        Optional<AppSettingDTO> one = settingService.findOne(id);
        if (one.isPresent()){
            return new ResponseEntity<>(one.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("setting not found", HttpStatus.NOT_FOUND);
        }
    }
}
