package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.web.resources;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.StudentService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentDTO;
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
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody  StudentDTO student){
        log.info("REST Request to save Student : {}", student);
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    StudentDTO updateStudent(@RequestBody  StudentDTO student,@PathVariable Long id){
        log.info("REST Request to update Student : {}", student);
        return studentService.update(student, id);
    }

    @GetMapping
    public List<StudentDTO> getAllStudent(){
        log.info("REST Request to get all Students");
        return studentService.getAll();
    }

    @GetMapping("/{id}")
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
    public void deleteStudent(@PathVariable Long id){
        log.info("REST Request to delete Student : {}", id);
        studentService.delete(id);
    }


}
