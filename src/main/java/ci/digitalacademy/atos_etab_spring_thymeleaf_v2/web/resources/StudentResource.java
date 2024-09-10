package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.web.resources;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.StudentService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentDTO;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/api/students")
public class StudentResource {
    private  final StudentService studentService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Student created")
    public ResponseEntity<StudentDTO> saveStudent(@Parameter(required = true ,description = "student required") @RequestBody  StudentDTO student){
        log.info("REST Request to save Student : {}", student);
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    StudentDTO updateStudent(@RequestBody  StudentDTO student,@PathVariable Long id){
        log.info("REST Request to update Student : {}", student);
        return studentService.update(student, id);
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students found"),
            @ApiResponse(responseCode = "404", description = "Students not found")
    })
    public List<StudentDTO> getAllStudent(){
        log.info("REST Request to get all Students");
        return studentService.getAll();
    }


    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        log.info("REST Request to get Student : {}", id);
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
    public void deleteStudent(@PathVariable Long id){
        log.info("REST Request to delete Student : {}", id);
        studentService.delete(id);
    }


}
