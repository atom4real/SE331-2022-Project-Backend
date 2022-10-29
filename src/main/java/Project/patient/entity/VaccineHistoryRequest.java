package Project.patient.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaccineHistoryRequest {
    String codeName;
    String fullName;
    Date vaccineDate;
}
