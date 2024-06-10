package com.smida.service.service.reportDetails;

import com.smida.service.exception.NoSuchReportDetailsException;
import com.smida.service.exception.NoSuchReportException;
import com.smida.service.model.Report;
import com.smida.service.model.ReportDetails;
import com.smida.service.repository.ReportDetailsRepository;
import com.smida.service.repository.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReportDetailsServiceImpl implements ReportDetailsService {

    private final ReportDetailsRepository reportDetailsRepository;
    private final ReportRepository reportRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReportDetailsServiceImpl.class);

    @Autowired
    public ReportDetailsServiceImpl(ReportDetailsRepository reportDetailsRepository, ReportRepository reportRepository) {
        this.reportDetailsRepository = reportDetailsRepository;
        this.reportRepository = reportRepository;
    }

    @Override
    public List<ReportDetails> findAll() {
        return new ArrayList<>(reportDetailsRepository.findAll());
    }

    @Override
    public ReportDetails findById(UUID id) {
        return reportDetailsRepository.findById(id)
                .orElseThrow(() -> new NoSuchReportDetailsException("ReportDetails not found with id " + id));
    }

    @Override
    public ReportDetails save(ReportDetails reportDetails) {

        Optional<ReportDetails> existingReportDetails = reportDetailsRepository.findById(reportDetails.getReportId());
        if (existingReportDetails.isPresent()) {
            logger.error("ReportDetails with id {} already exists", reportDetails.getReportId());
            throw new IllegalArgumentException("ReportDetails with this ID already exists");
        }

        Optional<Report> existingReport = reportRepository.findById(reportDetails.getReportId());
        if (existingReport.isEmpty()) {
            logger.warn("Report with id {} does not exist", reportDetails.getReportId());
            throw new NoSuchReportException("Report not found with id " + reportDetails.getReportId());
        }

        ReportDetails savedReportDetails = reportDetailsRepository.save(reportDetails);
        logger.info("Saved report details with id {}", savedReportDetails.getReportId());
        return savedReportDetails;
    }

    @Override
    public ReportDetails update(UUID id, ReportDetails reportDetails) {
        logger.info("Updating report details with id {}", id);
        reportDetails.setReportId(id);
        return reportDetailsRepository.save(reportDetails);
    }

    @Override
    public void deleteById(UUID id) {
        reportDetailsRepository.deleteById(id);
        logger.info("Deleted report details with id {}", id);
    }
}
