package Project.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import Project.comment.dto.CommentDto;
import Project.doctor.dto.DoctorDto;
import Project.security.dto.UserDto;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    Long id;
    UserDto user;
    DoctorDto doctor;
    List<VaccineHistoryDto> vaccineHistories;
    List<CommentDto> comments;
}
