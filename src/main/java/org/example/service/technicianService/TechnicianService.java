package org.example.service.technicianService;

import org.example.base.service.BaseService;
import org.example.model.Technician;
import org.example.model.enums.TechnicianStatus;

import java.util.List;

public interface TechnicianService extends BaseService<Technician, Long> {
    void loadAllTechnician();
    void showTechnicianWithStatus(TechnicianStatus status);
}
