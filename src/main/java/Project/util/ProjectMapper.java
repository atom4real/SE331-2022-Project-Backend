package Project.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import Project.doctor.dto.DoctorDto;
import Project.doctor.entity.Doctor;
import Project.patient.dto.PatientDto;
import Project.patient.entity.Patient;
import Project.security.dto.UserDto;
import Project.security.entity.User;
import Project.vaccine.dto.VaccineDto;
import Project.vaccine.entity.Vaccine;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(target = "authorities", expression = "java(user.getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    UserDto getUserDto(User user);
    List<UserDto> getUserDto(List<User> user);
    PatientDto getPatientDto(Patient patient);
    List<PatientDto> getPatientDto(List<Patient> patient);
    @Mappings({
            @Mapping(target = "fullName", expression = "java(doctor.getUser().getFirstname() + \" \" + doctor.getUser().getLastname())"),
            @Mapping(target = "imageUrls", expression = "java(doctor.getUser().getImageUrls())"),
            @Mapping(target = "UserId", expression = "java(doctor.getUser().getId())")
    })
    DoctorDto getDoctorDto(Doctor doctor);
    List<DoctorDto> getDoctorDto(List<Doctor> doctor);
    VaccineDto getVaccineDto(Vaccine vaccine);
    List<VaccineDto> getVaccineDto(List<Vaccine> vaccine);
}
