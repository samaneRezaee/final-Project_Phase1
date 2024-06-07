package org.example.repository.serveRepository;

import org.example.base.repository.BaseRepository;
import org.example.model.Serve;

import java.util.List;

public interface ServeRepository extends BaseRepository<Serve, Long> {
    boolean isExistServe(String title);
    List<Serve> loadAllServe();
}
