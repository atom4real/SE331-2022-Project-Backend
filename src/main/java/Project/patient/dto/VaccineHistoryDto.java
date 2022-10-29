package Project.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import Project.vaccine.dto.VaccineDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaccineHistoryDto {
    VaccineDto vaccine;
    String vaccineDate;
}
