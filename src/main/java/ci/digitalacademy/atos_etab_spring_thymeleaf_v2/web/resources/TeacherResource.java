package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.web.resources;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.TeacherService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.FileStudentDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.FileTeacherDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.TeacherDTO;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/teachers")
public class TeacherResource {

    private final TeacherService teacherService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Request to save teacher")
    @Operation(summary = "teacher new save", description = "this endpoint allow to save teacher")
    public ResponseEntity<TeacherDTO> saveTeacher(@RequestBody TeacherDTO teacherDTO){
        log.debug("REST Request to save Teacher : {}", teacherDTO);
        return new ResponseEntity<>(teacherService.saveUserAndTeacher(teacherDTO), HttpStatus.CREATED);
    }

    @PostMapping("/upload")
    public ResponseEntity<?>uploadPictureStudent(@ModelAttribute FileTeacherDTO fileTeacherDTO) throws IOException {
        TeacherDTO teacherDTO = teacherService.uploadTeacherPicture(fileTeacherDTO.getId(), fileTeacherDTO.getFile());
        if(teacherDTO != null){
            return new ResponseEntity<>(teacherDTO,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("teacher not found",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Teacher updated")
    @Operation(summary = "Update an existing Teacher", description = "Update an existing Teacher")
    public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTO teacherDTO,@PathVariable Long id){
        log.debug("REST Request to update Teacher : {}", teacherDTO);
        return new ResponseEntity<>(teacherService.update(teacherDTO, id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all Teachers", description = "Get all Teachers")
    public List<TeacherDTO> getAllTeacher(){
        log.debug("REST Request to get all Teachers");
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Request to get Teacher"),
            @ApiResponse(responseCode = "404", description = "Teacher not found")
    })
    @Operation(summary = "Get Teacher by id", description = "Get Teacher by id")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id){
        log.debug("REST Request to get by id Teacher : {}", id);
        Optional<TeacherDTO> one = teacherService.findOne(id);
        if (one.isPresent()){
            return new ResponseEntity<>(one.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>("teacher not found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Teacher by id", description = "Delete Teacher by id")
    public void deleteTeacher(@PathVariable Long id){
        teacherService.delete(id);
    }
}
