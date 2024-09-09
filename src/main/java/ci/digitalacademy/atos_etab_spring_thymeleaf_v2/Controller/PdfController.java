package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.Controller;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.StudentService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.TeacherService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.StudentDTO;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.TeacherDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.util.DocumentFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/pdf")
@RequiredArgsConstructor
public class PdfController {

    private final StudentService studentService;
    private final TeacherService teacherService;

    @GetMapping("/students")
    public String exportStudentsToPdf() throws IOException, DocumentFormatException {
        List<StudentDTO> studentDtos = studentService.getAll();

        String folderPath = "C:\\Users\\angam\\Desktop\\Atos\\Atos_Etab_Spring_thymeleaf_V2\\src\\main\\resources\\pdf";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = "students.pdf";
        File file = new File(folderPath + File.separator + fileName);

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            Document document = new Document();
            PdfWriter.getInstance(document, fileOutputStream);
            document.open();

            // Titre du document
            document.add(new Paragraph("Liste des élèves"));

            // Créer un tableau avec 6 colonnes (ou plus selon les champs à afficher)
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);

            // Ajouter des en-têtes de colonne
            table.addCell("Nom");
            table.addCell("Matricule");
            table.addCell("Téléphone");
            table.addCell("Téléphone du tuteur");
            table.addCell("Genre");
            table.addCell("Date de naissance");

            // Ajouter les informations des étudiants
            for (StudentDTO student : studentDtos) {
                table.addCell(student.getFirstName());
                table.addCell(student.getLastName());
                table.addCell(student.getMatricule());
                table.addCell(student.getPhoneNumber());
                table.addCell(student.getPhoneNumberFather());
                table.addCell(student.getGender().name());
                table.addCell(student.getBirthday().toString());
            }

            // Ajouter le tableau au document
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/reports";
    }
    @GetMapping("/teachers")
    public String exportTeachersToPdf() throws IOException, DocumentFormatException, DocumentException {
        List<TeacherDTO> teacherDtos = teacherService.getAll();

        // Définir le chemin de sauvegarde pour le fichier PDF
        String folderPath = "C:\\Users\\angam\\Desktop\\Atos\\Atos_Etab_Spring_thymeleaf_V2\\src\\main\\resources\\pdf";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // Créer le dossier s'il n'existe pas
        }

        // Nom du fichier PDF
        String fileName = "teachers.pdf";
        File file = new File(folderPath + File.separator + fileName);

        // Créer un document PDF et l'enregistrer dans le répertoire local
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            Document document = new Document();
            PdfWriter.getInstance(document, fileOutputStream);
            document.open();

            // Titre du document
            document.add(new Paragraph("Liste des professeurs"));
            document.add(new Paragraph("\n"));

            // Créer un tableau avec 6 colonnes
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100); // Largeur du tableau sur toute la page

            // Ajouter les en-têtes des colonnes
            table.addCell("Nom");
            table.addCell("Spécialité");
            table.addCell("Téléphone");
            table.addCell("Disponibilité");
            table.addCell("Genre");
            table.addCell("Date de naissance");


            for (TeacherDTO teacher : teacherDtos) {
                table.addCell( teacher.getFirstName());
                table.addCell( teacher.getLastName());
                table.addCell(teacher.getSpecialty());
                table.addCell(teacher.getPhoneNumber());
                table.addCell(teacher.isAvailable() ? "Disponible" : "Non disponible");
                table.addCell(teacher.getGender().name());
                table.addCell(teacher.getBirthday().toString());
            }
            document.add(table);
            document.close();
        }

        return "redirect:/reports";
    }
}
