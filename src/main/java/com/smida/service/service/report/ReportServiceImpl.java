package com.smida.service.service.report;

import com.smida.service.exception.NoSuchReportException;
import com.smida.service.model.Report;
import com.smida.service.repository.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Report findById(UUID id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new NoSuchReportException("Report not found with id " + id));
    }

    @Override
    public List<Report> findByCompanyId(UUID companyId) {
        return reportRepository.findByCompanyId(companyId);
    }

    @Override
    public Report save(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Report update(UUID id, Report report) {
        report.setId(id);
        return reportRepository.save(report);
    }

    @Override
    public void deleteById(UUID id) {
        reportRepository.deleteById(id);
    }

}
