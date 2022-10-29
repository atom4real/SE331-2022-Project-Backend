package Project.admin.controller;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import Project.admin.service.AdminService;
import Project.doctor.entity.Doctor;
import Project.doctor.service.DoctorService;
import Project.patient.entity.Patient;
import Project.patient.entity.VaccineHistoryRequest;
import Project.patient.service.PatientService;
import Project.security.entity.User;
import Project.security.service.UserService;
import Project.util.ProjectMapper;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    final AdminService adminService;
    final UserService userService;
    final DoctorService doctorService;
    final PatientService patientService;


    @PostMapping("/verify/patient/{id}")
    public ResponseEntity<?> verifyAsPatient(@PathVariable("id") Long id) {
        return ResponseEntity.ok(adminService.verifyAsPatient(id));
    }

    @PostMapping("/verify/doctor/{id}")
    public ResponseEntity<?> verifyAsDoctor(@PathVariable("id") Long id,@RequestBody Hospital hospital) {
        return ResponseEntity.ok(adminService.verifyAsDoctor(id, hospital.getHospital()));
    }

    @GetMapping("/patients")
    public ResponseEntity<?> getPatients(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, perPage);
        Page<Patient> patients = adminService.getAllPatient(pageRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-total-count", String.valueOf(patients.getTotalElements()));
        return new ResponseEntity<>(
                ProjectMapper.INSTANCE.getPatientDto(patients.getContent()),
                headers,
                HttpStatus.OK
        );
    }

    @GetMapping("/doctors")
    public ResponseEntity<?> getDoctors(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, perPage);
        Page<Doctor> doctors = adminService.getAllDoctor(pageRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-total-count", String.valueOf(doctors.getTotalElements()));
        return new ResponseEntity<>(
                ProjectMapper.INSTANCE.getDoctorDto(doctors.getContent()),
                headers,
                HttpStatus.OK
        );
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, perPage);
        Page<User> user = adminService.getAllUser(pageRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-total-count", String.valueOf(user.getTotalElements()));
        return new ResponseEntity<>(
                ProjectMapper.INSTANCE.getUserDto(user.getContent()),
                headers,
                HttpStatus.OK
        );
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        if(user.getAdmin() != null) {
            return ResponseEntity.ok(ProjectMapper.INSTANCE.getUserDto(adminService.getUser(id)));
        }
        if(user.getDoctor() != null) {
            return ResponseEntity.ok(ProjectMapper.INSTANCE.getDoctorDto(doctorService.getDoctor(user.getDoctor().getId())));
        }
        if(user.getPatient() != null) {
            return ResponseEntity.ok(ProjectMapper.INSTANCE.getPatientDto(patientService.getPatient(user.getPatient().getId())));
        }
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getUserDto(adminService.getUser(id)));
    }

    @GetMapping("/vaccines")
    public ResponseEntity<?> getVaccineHistoryRequests() {
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getVaccineDto(adminService.getAllVaccine()));
    }

    @PostMapping("/assign-doctor")
    public ResponseEntity<?> assignDoctor(@RequestBody AssignDoctorRequest request) {
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getPatientDto(adminService.addDoctorToPatient(request.getPatientID(), request.getDoctorID())));
    }

    @PostMapping("/add-vaccine-history")
    public ResponseEntity<?> addVaccineHistory(@RequestBody AddVaccineHistoryRequest request) {
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getPatientDto(adminService.addVaccineHistory(request.getPatientID(), request.getHistory())));
    }

    @GetMapping("/users/unverified")
    public ResponseEntity<?> getUnverifiedUsers(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, perPage);
        Page<User> user = adminService.getUnEnabledUser(pageRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-total-count", String.valueOf(user.getTotalElements()));
        return new ResponseEntity<>(
                ProjectMapper.INSTANCE.getUserDto(user.getContent()),
                headers,
                HttpStatus.OK
        );
    }
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Hospital {
    String hospital;
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class AssignDoctorRequest {
    Long patientID;
    Long doctorID;
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class AddVaccineHistoryRequest {
    Long patientID;
    List<VaccineHistoryRequest> history;
}
