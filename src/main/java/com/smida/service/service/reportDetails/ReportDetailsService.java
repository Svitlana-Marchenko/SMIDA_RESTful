package com.smida.service.service.reportDetails;

import com.smida.service.model.ReportDetails;

import java.util.List;
import java.util.UUID;

public interface ReportDetailsService {

    List<ReportDetails> findAll();
    ReportDetails findById(UUID id);
    ReportDetails save(ReportDetails reportDetails);
    ReportDetails update(UUID id, ReportDetails reportDetails);
    void deleteById(UUID id);
}
