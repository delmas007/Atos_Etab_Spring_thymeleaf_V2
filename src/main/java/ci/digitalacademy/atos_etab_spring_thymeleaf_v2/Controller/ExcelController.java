package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Controller;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.StudentService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.TeacherService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.UserService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.*;
import com.alibaba.excel.EasyExcel;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Controller
@RequestMapping("excels")
@RequiredArgsConstructor
public class ExcelController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final UserService userService;

    @GetMapping("/students")
    public void exportStudentToExcel(HttpServletResponse response) throws IOException {
        List<StudentDTO> studentDtos = studentService.getAll();

        List<StudentExcelDTO> studentExcelDTOS = studentDtos.stream().map(student -> {
            return StudentExcelDTO.builder()
                    .phoneNumberFather(student.getPhoneNumberFather())
                    .phoneNumber(student.getPhoneNumber())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .birthday(student.getBirthday())
                    .gender(student.getGender().name())
                    .matricule(student.getMatricule())
                    .build();
        }).toList();


        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=students.xlsx");

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            EasyExcel.write(outputStream, StudentExcelDTO.class)
                    .sheet("Students")
                    .doWrite(studentExcelDTOS);

            try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(outputStream.toByteArray()))) {
                Sheet sheet = workbook.getSheet("Students");

                for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
                    sheet.autoSizeColumn(i);
                }

                try (ServletOutputStream responseOutputStream = response.getOutputStream()) {
                    workbook.write(responseOutputStream);
                    responseOutputStream.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to write Excel file: " + e.getMessage(), e);
        }
    }


    @GetMapping("/teachers")
    public void exportTeacherToExcel(HttpServletResponse response) throws IOException {
        List<TeacherDTO> teacherDTOS = teacherService.getAll();

        List<TeacherExcelDTO> teacherExcelDTOS = teacherDTOS.stream().map(teacherDTO -> {
            String availabilityStatus = teacherDTO.isAvailable() ? "Disponible" : "Non disponible";
            TeacherExcelDTO dto = TeacherExcelDTO.builder()
                    .available(availabilityStatus)
                    .phoneNumber(teacherDTO.getPhoneNumber())
                    .firstName(teacherDTO.getFirstName())
                    .lastName(teacherDTO.getLastName())
                    .birthday(teacherDTO.getBirthday())
                    .gender(teacherDTO.getGender().name())
                    .specialty(teacherDTO.getSpecialty())
                    .build();
            return dto;
        }).toList();


        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=teachers.xlsx");


        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            EasyExcel.write(outputStream, TeacherExcelDTO.class)
                    .sheet("Teachers")
                    .doWrite(teacherExcelDTOS);


            try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(outputStream.toByteArray()))) {
                Sheet sheet = workbook.getSheet("Teachers");

                for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
                    sheet.autoSizeColumn(i);
                }

                try (ServletOutputStream responseOutputStream = response.getOutputStream()) {
                    workbook.write(responseOutputStream);
                    responseOutputStream.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to write Excel file: " + e.getMessage(), e);
        }
    }


    @GetMapping("/users")
    public void exportUserToExcel(HttpServletResponse response) throws IOException {
        List<UserDTO> userDTOS = userService.getAll();

        List<UserExcelDto> userExcelDtos = userDTOS.stream().map(userDTO -> {
            UserExcelDto dto = UserExcelDto.builder()
                    .pseudo(userDTO.getPseudo())
                    .creationDate(String.valueOf(userDTO.getCreationDate()))
                    .roleUser(userDTO.getRoleUser().stream()
                            .map(RoleUserDTO::getRole)
                            .collect(Collectors.joining(", ")))
//                    .school(userDTO.getSchool().getName())
                    .build();
            return dto;
        }).toList();


        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");


        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            EasyExcel.write(outputStream, UserExcelDto.class)
                    .sheet("Users")
                    .doWrite(userExcelDtos);

            try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(outputStream.toByteArray()))) {
                Sheet sheet = workbook.getSheet("Users");

                for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
                    sheet.autoSizeColumn(i);
                }

                try (ServletOutputStream responseOutputStream = response.getOutputStream()) {
                    workbook.write(responseOutputStream);
                    responseOutputStream.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to write Excel file: " + e.getMessage(), e);
        }
    }


}
