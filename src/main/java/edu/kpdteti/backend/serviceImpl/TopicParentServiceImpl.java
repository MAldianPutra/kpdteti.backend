package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.TopicParent;
import edu.kpdteti.backend.model.response.topicParent.GetAllTopicParentsResponse;
import edu.kpdteti.backend.repository.TopicParentRepository;
import edu.kpdteti.backend.service.TopicParentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicParentServiceImpl implements TopicParentService {

    private final TopicParentRepository topicParentRepository;

    @Autowired
    public TopicParentServiceImpl(TopicParentRepository topicParentRepository) {
        this.topicParentRepository = topicParentRepository;
    }

    @Override
    public List<GetAllTopicParentsResponse> getAllTopicParents() {
        List<TopicParent> topicParents = topicParentRepository.findAll();
        if(topicParents.isEmpty()) {
            throw new EntityNotFoundException("No Topic Parent in database");
        }
        List<GetAllTopicParentsResponse> responses = new ArrayList<>();
        topicParents.forEach(topicParent -> {
            GetAllTopicParentsResponse response = new GetAllTopicParentsResponse();
            BeanUtils.copyProperties(topicParent, response);
            responses.add(response);
        });
        return responses;
    }

}
