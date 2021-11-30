package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.Topic;
import edu.kpdteti.backend.model.response.topic.GetTopicResponse;
import edu.kpdteti.backend.model.response.topic.GetTopicsByParentResponse;
import edu.kpdteti.backend.repository.TopicRepository;
import edu.kpdteti.backend.service.TopicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public List<GetTopicsByParentResponse> getTopicsByParent(String topicParentId) {
        List<Topic> topics = topicRepository.findAllByTopicParentId(topicParentId);
        if(topics.isEmpty()) {
            throw new EntityNotFoundException("Topics not found with ParentId " + topicParentId);
        }
        List<GetTopicsByParentResponse> responses = new ArrayList<>();
        topics.forEach(topic -> {
            GetTopicsByParentResponse response = new GetTopicsByParentResponse();
            BeanUtils.copyProperties(topic, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public GetTopicResponse getTopic(String topicId) {
        Topic topic = topicRepository.findByTopicId(topicId);
        if(topic == null) {
            throw new EntityNotFoundException("Topic not found with id " + topicId);
        }
        GetTopicResponse response = new GetTopicResponse();
        BeanUtils.copyProperties(topic, response);
        return response;
    }



}
