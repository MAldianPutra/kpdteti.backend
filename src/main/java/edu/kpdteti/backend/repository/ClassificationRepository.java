package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.Classification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassificationRepository extends MongoRepository<Classification, String> {


}
