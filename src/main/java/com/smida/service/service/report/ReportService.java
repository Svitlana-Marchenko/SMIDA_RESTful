package com.smida.service.service.report;

import com.smida.service.model.Report;

import java.util.List;
import java.util.UUID;

public interface ReportService {
    List<Report> findAll();
    Report findById(UUID id);
    List<Report> findByCompanyId(UUID companyId);
    Report save(Report report);
    Report update(UUID id, Report report);
    void deleteById(UUID id);

}
