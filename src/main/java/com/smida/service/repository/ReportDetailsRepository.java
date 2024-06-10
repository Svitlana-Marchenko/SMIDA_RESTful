package com.smida.service.repository;

import com.smida.service.model.ReportDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ReportDetailsRepository extends MongoRepository<ReportDetails, UUID> {

}
