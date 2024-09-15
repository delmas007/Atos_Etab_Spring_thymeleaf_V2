package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.web.resources;


import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.UserService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.UserDTO;
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
@RequestMapping("/api/users")
public class UserResource {
    private final UserService userService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Request to save User")
    @Operation(summary = "User new save", description = "this endpoint allow to save User")
    public ResponseEntity<UserDTO> saveUser(UserDTO userDTO){
        log.debug("REST Request to save User : {}", userDTO);
        return new  ResponseEntity<>(userService.save(userDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "User updated")
    @Operation(summary = "Update an existing User", description = "Update an existing User")
    public ResponseEntity<UserDTO> updateUser(UserDTO userDTO, Long id){
        log.debug("REST Request to update User : {}", userDTO);
        return new ResponseEntity<>(userService.update(userDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "User deleted")
    @Operation(summary = "Delete an existing User", description = "Delete an existing User")
    public void deleteUser(Long id){
        log.debug("REST Request to delete User : {}", id);
        userService.delete(id);
    }

    @GetMapping
    @Operation(summary = "Get all Users", description = "Get all Users")
    public List<UserDTO> getAllUsers(){
        log.debug("REST Request to get all Users");
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Request to get User"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @Operation(summary = "Get User by id", description = "Get User by id")
    public ResponseEntity<?> getUser(Long id){
        log.debug("REST Request to get User : {}", id);
        Optional<UserDTO> one = userService.findOne(id);
        if (one.isPresent()){
            return new ResponseEntity<>(one.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
