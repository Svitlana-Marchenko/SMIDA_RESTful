package com.smida.service.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.UUID;

@Document(collection = "report_details")
@Getter
@Setter
public class ReportDetails {

    @Id
    private UUID reportId;
    private String financialData;
    private String comments;
}
