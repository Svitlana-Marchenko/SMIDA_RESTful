package com.smida.service.mapper;

import com.smida.service.dto.CompanyDto;
import com.smida.service.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyDto companyToCompanyDto(Company company);

   Company companyDtoToCompany (CompanyDto companyDto);
}