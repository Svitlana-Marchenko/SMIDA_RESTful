package com.smida.service.controller;

import com.smida.service.dto.ReportDto;
import com.smida.service.exception.NoSuchReportException;
import com.smida.service.exception.NoSuchUserException;
import com.smida.service.mapper.ReportMapper;
import com.smida.service.model.Report;
import com.smida.service.service.report.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/report")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<List<ReportDto>> getAllReports() {
        List<Report> reports = reportService.findAll();
        return ResponseEntity.ok(reports.stream().map(ReportMapper.INSTANCE::reportToReportDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getReportById(@PathVariable UUID id) {
        Report report = reportService.findById(id);
        return ResponseEntity.ok(ReportMapper.INSTANCE.reportToReportDto(report));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<ReportDto>> getReportsByCompanyId(@PathVariable UUID companyId) {
        List<Report> reports = reportService.findByCompanyId(companyId);
        return ResponseEntity.ok(reports.stream().map(ReportMapper.INSTANCE::reportToReportDto).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ReportDto> createReport(@RequestBody ReportDto reportDTO) {
        Report createdReport = reportService.save(ReportMapper.INSTANCE.reportDtoToReport(reportDTO));
        logger.info("Created report with id {}", createdReport.getId());
        return ResponseEntity.ok(ReportMapper.INSTANCE.reportToReportDto(createdReport));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportDto> updateReport(@PathVariable UUID id, @RequestBody ReportDto reportDTO) {
        logger.info("Updating report with id {}", id);
        Report updatedReport = reportService.update(id, ReportMapper.INSTANCE.reportDtoToReport(reportDTO));
        return ResponseEntity.ok(ReportMapper.INSTANCE.reportToReportDto(updatedReport));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable UUID id) {
        logger.info("Deleting report with id {}", id);
        reportService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchReportException.class)
    public ResponseEntity<String> handleNoSuchReportException(NoSuchUserException e) {
        String errorMessage = "ERROR: " + e.getMessage();
        logger.error(errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Report not found: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String errorMessage = "ERROR: " + e.getMessage();
        logger.error(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
