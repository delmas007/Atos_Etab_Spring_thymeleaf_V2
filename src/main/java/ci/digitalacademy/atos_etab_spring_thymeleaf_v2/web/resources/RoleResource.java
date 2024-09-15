package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.web.resources;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.RoleUserService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.RoleUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/roles")
public class RoleResource {
    private final RoleUserService roleUserService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Request to save role")
    @Operation(summary = "role new save", description = "this endpoint allow to save role")
    public ResponseEntity<RoleUserDTO> saveRole(@RequestBody RoleUserDTO roleUserDTO){
        log.debug("REST Request to save Role : {}", roleUserDTO);
        return new ResponseEntity<>(roleUserService.save(roleUserDTO), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiResponse(responseCode = "200", description = "Role updated")
    @Operation(summary = "Update an existing role", description = "Update an existing role")
    public ResponseEntity<RoleUserDTO> updateRole(@RequestBody RoleUserDTO roleUserDTO){
        log.debug("REST Request to update Role : {}", roleUserDTO);
        return new ResponseEntity<>(roleUserService.update(roleUserDTO), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all roles", description = "Get all roles")
    public ResponseEntity<?> getAllRole(){
        log.debug("REST Request to get all roles");
        return new ResponseEntity<>(roleUserService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to get role")
    @Operation(summary = "Get role by id", description = "Get role by id")
    public ResponseEntity<?> getOneRole(@PathVariable Long id){
        log.debug("REST Request to get role : {}", id);
        Optional<RoleUserDTO> one = roleUserService.findOne(id);
        if (one.isPresent()){
            return new ResponseEntity<>(one.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("role not found", HttpStatus.NOT_FOUND);
        }
    }
}
