package com.smida.service.controller;

import com.smida.service.dto.CompanyDto;
import com.smida.service.exception.NoSuchCompanyException;
import com.smida.service.exception.NoSuchUserException;
import com.smida.service.mapper.CompanyMapper;
import com.smida.service.model.Company;
import com.smida.service.service.company.CompanyService;
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
@RequestMapping("api/v1/company")
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<Company> companies = companyService.findAll();
        return ResponseEntity.ok(companies.stream().map(CompanyMapper.INSTANCE::companyToCompanyDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable UUID id) {
        CompanyDto company = CompanyMapper.INSTANCE.companyToCompanyDto(companyService.findById(id));
        return ResponseEntity.ok(company);
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDTO) {
        Company createdCompany = companyService.save(CompanyMapper.INSTANCE.companyDtoToCompany(companyDTO));
        logger.info("Created company with id {}", createdCompany.getId());
        return ResponseEntity.ok(CompanyMapper.INSTANCE.companyToCompanyDto(createdCompany));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable UUID id, @RequestBody CompanyDto companyDTO) {
        logger.info("Updating company with id {}", id);
        Company updatedCompany = companyService.update(id, CompanyMapper.INSTANCE.companyDtoToCompany(companyDTO));
        return ResponseEntity.ok(CompanyMapper.INSTANCE.companyToCompanyDto(updatedCompany));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable UUID id) {
        logger.info("Deleting company with id {}", id);
        companyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchCompanyException.class)
    public ResponseEntity<String> handleNoSuchCompanyException(NoSuchUserException e) {
        String errorMessage = "ERROR: " + e.getMessage();
        logger.error(errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String errorMessage = "ERROR: " + e.getMessage();
        logger.error(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
