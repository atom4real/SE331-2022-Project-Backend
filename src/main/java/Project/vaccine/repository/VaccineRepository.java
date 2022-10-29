package Project.vaccine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Project.vaccine.entity.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    Vaccine findByCodeName(String codeName);
}
