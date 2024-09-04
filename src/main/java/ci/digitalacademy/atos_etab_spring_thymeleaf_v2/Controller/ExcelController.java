package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Controller;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.StudentService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.TeacherService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentExcelDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.TeacherDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.TeacherExcelDTO;
import com.alibaba.excel.EasyExcel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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

@Controller
@RequestMapping("excels")
@RequiredArgsConstructor
public class ExcelController {

    private final StudentService studentService;
    private final TeacherService teacherService;

    @PostMapping("/students")
    public String exportStudentToExcel() throws IOException {
        List<StudentDTO> studentDtos = studentService.getAll();

        List<StudentExcelDTO> studentExcelDTOS = studentDtos.stream().map(student -> {
            StudentExcelDTO dto = StudentExcelDTO.builder()
                    .phoneNumberFather(student.getPhoneNumberFather())
                    .phoneNumber(student.getPhoneNumber())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .birthday(student.getBirthday())
                    .gender(student.getGender().name())
                    .matricule(student.getMatricule())
                    .build();
            return dto;
        }).toList();

        String folderPath = "C:\\Users\\angam\\Desktop\\Atos\\Atos_Etab_Spring_thymeleaf_V2\\src\\main\\resources\\excel";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }


        String fileName = "students.xlsx";
        File file = new File(folderPath + File.separator + fileName);

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            EasyExcel.write(fileOutputStream, StudentExcelDTO.class)
                    .sheet("Students")
                    .doWrite(studentExcelDTOS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to write Excel file: " + e.getMessage(), e);
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {

            Sheet sheet = workbook.getSheet("Students");


            for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to adjust column widths: " + e.getMessage(), e);
        }

        return "redirect:/reports";
    }

    @PostMapping("/teachers")
    public String exportTeacherToExcel() throws IOException {
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

        String folderPath = "C:\\Users\\angam\\Desktop\\Atos\\Atos_Etab_Spring_thymeleaf_V2\\src\\main\\resources\\excel";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }


        String fileName = "teachers.xlsx";
        File file = new File(folderPath + File.separator + fileName);


        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            EasyExcel.write(fileOutputStream, TeacherExcelDTO.class)
                    .sheet("Teachers")
                    .doWrite(teacherExcelDTOS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to write Excel file: " + e.getMessage(), e);
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {

            Sheet sheet = workbook.getSheet("Teachers");


            for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to adjust column widths: " + e.getMessage(), e);
        }

        return "redirect:/reports";
    }

}
