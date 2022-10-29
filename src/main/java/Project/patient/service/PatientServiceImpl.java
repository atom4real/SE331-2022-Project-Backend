package Project.patient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Project.patient.dao.PatientDao;
import Project.patient.entity.Patient;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientDao patientDao;

    @Override
    public Patient getPatient(Long id) {
        return patientDao.getPatient(id);
    }
}
