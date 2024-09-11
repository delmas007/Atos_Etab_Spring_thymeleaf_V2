package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Controller;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.StudentService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.TeacherService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.UserService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.RoleUserDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.TeacherDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.UserDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.util.DocumentFormatException;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletOutputStream;

import org.springframework.http.ResponseEntity;


@Controller
@RequestMapping("/pdf")
@RequiredArgsConstructor
public class PdfController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final UserService userService;

    @GetMapping("/students")
    public ResponseEntity<byte[]> exportStudentsToPdf() throws IOException, DocumentException {
        List<StudentDTO> studentDtos = studentService.getAll();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();

        document.add(new Paragraph("Liste des élèves"));

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);

        table.addCell("Nom");
        table.addCell("Prénom");
        table.addCell("Matricule");
        table.addCell("Téléphone");
        table.addCell("Téléphone du tuteur");
        table.addCell("Genre");
        table.addCell("Date de naissance");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (StudentDTO student : studentDtos) {
            table.addCell(student.getLastName());
            table.addCell(student.getFirstName());
            table.addCell(student.getMatricule());
            table.addCell(student.getPhoneNumber());
            table.addCell(student.getPhoneNumberFather());
            table.addCell(student.getGender().name());
            table.addCell(student.getBirthday().format(formatter));
        }

        document.add(table);
        document.close();

        byte[] pdfBytes = baos.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "students.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }





    @GetMapping("/teachers")
    public ResponseEntity<byte[]> exportTeachersToPdf() throws IOException, DocumentException {
        List<TeacherDTO> teacherDtos = teacherService.getAll();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();

        document.add(new Paragraph("Liste des professeurs"));

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);

        table.addCell("Prénom");
        table.addCell("Nom");
        table.addCell("Spécialité");
        table.addCell("Téléphone");
        table.addCell("Disponibilité");
        table.addCell("Genre");
        table.addCell("Date de naissance");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (TeacherDTO teacher : teacherDtos) {
            table.addCell(teacher.getFirstName());
            table.addCell(teacher.getLastName());
            table.addCell(teacher.getSpecialty());
            table.addCell(teacher.getPhoneNumber());
            table.addCell(teacher.isAvailable() ? "Disponible" : "Non disponible");
            table.addCell(teacher.getGender().name());
            // Format LocalDate using DateTimeFormatter
            table.addCell(teacher.getBirthday().format(formatter));
        }

        document.add(table);
        document.close();

        byte[] pdfBytes = baos.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "teachers.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }




    @GetMapping("/users")
    public ResponseEntity<byte[]> exportUsersToPdf() throws IOException, DocumentException {
        List<UserDTO> userDtos = userService.getAll();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        document.add(new Paragraph("Liste des utilisateurs"));
        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        table.addCell("Pseudo");
        table.addCell("Date de création");
        table.addCell("Rôle");
//        table.addCell("École");
        table.addCell("Statut");

        for (UserDTO user : userDtos) {
            table.addCell(user.getPseudo());
            table.addCell(user.getCreationDate().toString());
            table.addCell(user.getRoleUser().stream()
                    .map(RoleUserDTO::getRole)
                    .collect(Collectors.joining(", ")));
//            table.addCell(user.getSchool().getName()); // École
            table.addCell(user.getActive() ? "Actif" : "Inactif");
        }

        document.add(table);
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

        return new ResponseEntity<>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);
    }



}
