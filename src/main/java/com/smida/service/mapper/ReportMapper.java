package com.smida.service.mapper;

import com.smida.service.dto.ReportDto;
import com.smida.service.model.Report;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    ReportDto reportToReportDto(Report report);

    Report reportDtoToReport (ReportDto reportDto);
}
