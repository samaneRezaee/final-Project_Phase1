package org.example.repository.serveRepository;

import org.example.base.repository.BaseRepository;
import org.example.model.Serve;

public interface ServeRepository extends BaseRepository<Serve, Long> {
    boolean isExistServe(String title);
}
