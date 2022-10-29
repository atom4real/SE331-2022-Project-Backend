package Project.doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    Long id;
    Long UserId;
    String fullName;
    String hospital;
    String imageUrls;
}
