package com.smida.service.controller;

import com.smida.service.dto.ReportDetailsDto;
import com.smida.service.exception.NoSuchReportDetailsException;
import com.smida.service.mapper.ReportDetailsMapper;
import com.smida.service.model.ReportDetails;
import com.smida.service.service.reportDetails.ReportDetailsService;
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
@RequestMapping("api/v1/report-details")
public class ReportDetailsController {

    private static final Logger logger = LoggerFactory.getLogger(ReportDetailsController.class);

    private final ReportDetailsService reportDetailsService;

    @Autowired
    public ReportDetailsController(ReportDetailsService reportDetailsService) {
        this.reportDetailsService = reportDetailsService;
    }

    @GetMapping
    public ResponseEntity<List<ReportDetailsDto>> getAllReportDetails() {
        List<ReportDetails> reportDetails = reportDetailsService.findAll();
        return ResponseEntity.ok(reportDetails.stream().map(ReportDetailsMapper.INSTANCE::reportDetailsToReportDetailsDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDetailsDto> getReportDetailsById(@PathVariable UUID id) {
        ReportDetails reportDetails = reportDetailsService.findById(id);
        return ResponseEntity.ok(ReportDetailsMapper.INSTANCE.reportDetailsToReportDetailsDto(reportDetails));
    }

    @PostMapping
    public ResponseEntity<ReportDetailsDto> createReportDetails(@RequestBody ReportDetailsDto reportDetailsDTO) {
        ReportDetails createdReportDetails = reportDetailsService.save(ReportDetailsMapper.INSTANCE.reportDetailsDtoToReportDetails(reportDetailsDTO));
        logger.info("Created report details with id {}", createdReportDetails.getReportId());
        return ResponseEntity.ok(ReportDetailsMapper.INSTANCE.reportDetailsToReportDetailsDto(createdReportDetails));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportDetailsDto> updateReportDetails(@PathVariable UUID id, @RequestBody ReportDetailsDto reportDetailsDTO) {
        logger.info("Updating report details with id {}", id);
        ReportDetails updatedReportDetails = reportDetailsService.update(id, ReportDetailsMapper.INSTANCE.reportDetailsDtoToReportDetails(reportDetailsDTO));
        return ResponseEntity.ok(ReportDetailsMapper.INSTANCE.reportDetailsToReportDetailsDto(updatedReportDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReportDetails(@PathVariable UUID id) {
        logger.info("Deleting report details with id {}", id);
        reportDetailsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchReportDetailsException.class)
    public ResponseEntity<String> handleNoSuchReportDetailsException(NoSuchReportDetailsException e) {
        String errorMessage = "ERROR: " + e.getMessage();
        logger.error(errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ReportDetails not found: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String errorMessage = "ERROR: " + e.getMessage();
        logger.error(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

