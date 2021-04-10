package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.Topic;
import edu.kpdteti.backend.model.dto.TopicDto;
import edu.kpdteti.backend.model.response.topic.GetTopicByTopicParentResponse;
import edu.kpdteti.backend.model.response.topic.GetTopicResponse;
import edu.kpdteti.backend.repository.TopicRepository;
import edu.kpdteti.backend.service.TopicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public GetTopicByTopicParentResponse getTopicByTopicParent(Long topicParentId) {
        List<Topic> topics = topicRepository.findAllByTopicParent_TopicParentId(topicParentId);
        List<TopicDto> topicDtos = new ArrayList<>();
        topics.forEach(topic -> topicDtos.add(toTopicDto(topic)));
        return new GetTopicByTopicParentResponse(topicDtos);
    }

    @Override
    public GetTopicResponse getTopic(Long topicId) {
        Topic topic = topicRepository.findByTopicId(topicId);
        GetTopicResponse response = new GetTopicResponse();
        BeanUtils.copyProperties(topic, response);
        return response;
    }

    private TopicDto toTopicDto(Topic topic) {
        TopicDto topicDto = new TopicDto();
        BeanUtils.copyProperties(topic, topicDto);
        return topicDto;
    }
}
