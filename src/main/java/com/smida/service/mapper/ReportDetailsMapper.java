package com.smida.service.mapper;

import com.smida.service.dto.ReportDetailsDto;
import com.smida.service.model.ReportDetails;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReportDetailsMapper {

    ReportDetailsMapper INSTANCE = Mappers.getMapper(ReportDetailsMapper.class);

    ReportDetailsDto reportDetailsToReportDetailsDto(ReportDetails reportDetails);

    ReportDetails reportDetailsDtoToReportDetails (ReportDetailsDto reportDetailsDto);
}
