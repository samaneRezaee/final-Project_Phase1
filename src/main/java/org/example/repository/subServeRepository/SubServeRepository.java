package org.example.repository.subServeRepository;

import org.example.base.repository.BaseRepository;
import org.example.model.SubServe;

import java.util.List;

public interface SubServeRepository extends BaseRepository<SubServe, Long> {
    boolean isExistSubServe(String title);
    List<SubServe> loadAllSubServe();
}
