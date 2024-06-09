package org.example.service.subServeService;

import org.example.base.service.BaseService;
import org.example.model.SubServe;

public interface SubServeService extends BaseService<SubServe, Long> {
    boolean isExistSubServe(String title);
    void loadAllSubServe();
    void findByServeId(Long id);
}
