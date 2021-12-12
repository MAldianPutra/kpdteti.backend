package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.response.classification.GetClassificationResponse;

public interface ClassificationService {

    GetClassificationResponse getClassification(String classificationId);

}
