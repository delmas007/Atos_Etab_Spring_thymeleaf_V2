package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.web.resources;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.StudentService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.FileStudentDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.RegistrationStudentDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.ResponseRegisterStudentDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/students")
public class StudentResource {
    private  final StudentService studentService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Student created")
    @Operation(summary = "Create a new student", description = "Create a new student")
    public ResponseEntity<StudentDTO> saveStudent(@Parameter(required = true ,description = "student required") @RequestBody  StudentDTO student){
        log.debug("REST Request to save Student : {}", student);
        return new ResponseEntity<>(studentService.saveUserAndStudent(student), HttpStatus.CREATED);
    }

    @PostMapping("/upload")
    public ResponseEntity<?>uploadPictureStudent(@ModelAttribute FileStudentDTO fileStudentDTO) throws IOException {
        StudentDTO studentDTO = studentService.uploadStudentPicture(fileStudentDTO.getId(), fileStudentDTO.getFile());
        if(studentDTO != null){
            return new ResponseEntity<>(studentDTO,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("student not found",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @Operation(summary = "Update an existing student", description = "Update an existing student")
    StudentDTO updateStudent(@RequestBody  StudentDTO student,@PathVariable Long id){
        log.debug("REST Request to update Student : {}", student);
        return studentService.update(student, id);
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students found"),
            @ApiResponse(responseCode = "404", description = "Students not found")
    })
    @Operation(summary = "Get all students", description = "Get all students")
    public List<StudentDTO> getAllStudent(){
        log.debug("REST Request to get all Students");
        return studentService.getAll();
    }


    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @Operation(summary = "Get a student by id", description = "Get a student by id")
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        log.debug("REST Request to get Student : {}", id);
        Optional<StudentDTO> studentDTO = studentService.findOne(id);
        if (studentDTO.isPresent()){
            return new ResponseEntity<>(studentDTO.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("student not fond",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student deleted"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @Operation(summary = "Delete a student by id", description = "Delete a student by id")
    public void deleteStudent(@PathVariable Long id){
        log.debug("REST Request to delete Student : {}", id);
        studentService.delete(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registration")
    public ResponseRegisterStudentDTO registrationStudent(@RequestBody RegistrationStudentDTO registrationStudentDTO){
        log.debug("REST Request to registration Student : {}", registrationStudentDTO);
        return studentService.registrationStudent(registrationStudentDTO);
    }


}
