package Project.patient.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import Project.patient.entity.VaccineHistory;
import Project.patient.repository.VaccineHistoryRepository;

@Repository
public class VaccineHistoryDaoImpl implements VaccineHistoryDao {

    @Autowired
    VaccineHistoryRepository vaccineHistoryRepository;

    @Override
    public VaccineHistory addVaccineHistory(VaccineHistory vaccineHistory) {
        return vaccineHistoryRepository.save(vaccineHistory);
    }
}
