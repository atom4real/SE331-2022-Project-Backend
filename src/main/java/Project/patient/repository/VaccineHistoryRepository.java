package Project.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Project.patient.entity.VaccineHistory;

public interface VaccineHistoryRepository extends JpaRepository<VaccineHistory, Long> {
}
