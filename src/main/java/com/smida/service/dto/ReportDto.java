package com.smida.service.dto;

import com.smida.service.model.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    private UUID id;

    private Company company;

    private Timestamp reportDate;

    private BigDecimal totalRevenue;

    private BigDecimal netProfit;
}
