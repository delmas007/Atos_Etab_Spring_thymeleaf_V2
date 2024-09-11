package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.mapping;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.model.Absence;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.AbsenceDTO;

@
public final class AbsenceMapping {

    private AbsenceMapping() {
    }

    public static void partialUpdate(Absence absence, AbsenceDTO absenceDTO) {
        if (absenceDTO.getAbsenceDate() != null) {
            absence.setAbsenceDate(absenceDTO.getAbsenceDate());
        }
        if (absenceDTO.getAbsenceNumber() != null) {
            absence.setAbsenceNumber(absenceDTO.getAbsenceNumber());
        }
    }
}
