package com.smida.service.service.company;

import com.smida.service.model.Company;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    List<Company> findAll();

    Company findById(UUID id);

    Company save(Company company);

    Company update(UUID id, Company company);

    void deleteById(UUID id);

}
