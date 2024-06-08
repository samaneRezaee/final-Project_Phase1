package org.example.repository.technicianRepository;

import org.example.base.repository.BaseRepository;
import org.example.model.SubServe;
import org.example.model.Technician;
import org.example.model.enums.TechnicianStatus;

import java.util.List;

public interface TechnicianRepository extends BaseRepository<Technician, Long> {
    List<Technician> loadAllTechnician();
    List<Technician> showTechnicianWithStatus(TechnicianStatus status);
}
