package Project.doctor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import Project.comment.entity.CommentRequest;
import Project.doctor.service.DoctorService;
import Project.patient.entity.Patient;
import Project.patient.service.PatientService;
import Project.util.ProjectMapper;

@Controller
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    final DoctorService doctorService;
    final PatientService patientService;



    @GetMapping("/{id}/patients")
    public ResponseEntity<?> getPatientInCare(
            @PathVariable("id") Long id,
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, perPage);
        Page<Patient> patients = doctorService.getPatientInCare(id, pageRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-total-count", String.valueOf(patients.getTotalElements()));
        return new ResponseEntity<>(
                ProjectMapper.INSTANCE.getPatientDto(patients.getContent()),
                headers,
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}/patients/{patientId}")
    public ResponseEntity<?> getPatientInCareById(@PathVariable("id") Long id, @PathVariable("patientId") Long patientId) {
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getPatientDto(patientService.getPatient(patientId)));
    }

    @PostMapping("/comment")
    public ResponseEntity<?> addComment(@RequestBody CommentRequest comment) {
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getPatientDto(doctorService.addComment(comment)));
    }

}
