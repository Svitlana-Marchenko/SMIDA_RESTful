package com.smida.service.service.company;

import com.smida.service.exception.NoSuchCompanyException;
import com.smida.service.model.Company;
import com.smida.service.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Override
    public List<Company> findAll() {
        return new ArrayList<>(companyRepository.findAll());
    }

    @Override
    public Company findById(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new NoSuchCompanyException("Company not found with id " + id));
    }

    @Override
    public Company save(Company company) {
        company.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        Company savedCompany = companyRepository.save(company);
        logger.info("Saved company with id {}", savedCompany.getId());
        return savedCompany;
    }

    @Override
    public Company update(UUID id, Company company) {
        company.setId(id);
        logger.info("Updated company with id {}", id);
        return companyRepository.save(company);
    }

    @Override
    public void deleteById(UUID id) {
        companyRepository.deleteById(id);
        logger.info("Deleted company with id {}", id);
    }
}
