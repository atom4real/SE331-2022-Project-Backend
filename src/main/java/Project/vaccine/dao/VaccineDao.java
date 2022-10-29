package Project.vaccine.dao;

import Project.vaccine.entity.Vaccine;

import java.util.List;

public interface VaccineDao {
    Vaccine addVaccine(Vaccine vaccine);
    Vaccine getVaccine(String codeName);
    List<Vaccine> getAllVaccines();
}
