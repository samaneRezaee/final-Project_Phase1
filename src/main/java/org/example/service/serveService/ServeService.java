package org.example.service.serveService;

import org.example.base.service.BaseService;
import org.example.model.Serve;

import java.util.List;

public interface ServeService extends BaseService<Serve, Long> {
    boolean isExistServe(String title);
    void loadAllServe();

}
