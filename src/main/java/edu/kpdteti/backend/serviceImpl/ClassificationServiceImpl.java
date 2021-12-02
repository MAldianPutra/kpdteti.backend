package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.Classification;
import edu.kpdteti.backend.model.response.classification.GetClassificationResponse;
import edu.kpdteti.backend.repository.ClassificationRepository;
import edu.kpdteti.backend.service.ClassificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ClassificationServiceImpl implements ClassificationService {

    private final ClassificationRepository classificationRepository;

    @Autowired
    public ClassificationServiceImpl(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public GetClassificationResponse getClassification(String classificationId) {
        Classification classification = classificationRepository.findClassificationByClassificationId(classificationId);
        if(classification == null) {
            throw new EntityNotFoundException("Classification not found with id " + classificationId);
        }
        GetClassificationResponse response = new GetClassificationResponse();
        BeanUtils.copyProperties(classification, response);
        response.setMessage("");
        return response;
    }
}
