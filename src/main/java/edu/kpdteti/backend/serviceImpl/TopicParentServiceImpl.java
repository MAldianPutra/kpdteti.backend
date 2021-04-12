package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.TopicParent;
import edu.kpdteti.backend.model.dto.TopicParentDto;
import edu.kpdteti.backend.model.response.topicParent.GetAllTopicParentResponse;
import edu.kpdteti.backend.repository.TopicParentRepository;
import edu.kpdteti.backend.service.TopicParentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public GetAllTopicParentResponse getAllTopicParent() {
        List<TopicParent> topicParents = topicParentRepository.findAll();
        List<TopicParentDto> topicParentDtos = new ArrayList<>();
        topicParents.forEach(topicParent -> topicParentDtos.add(toTopicParentDto(topicParent)));
        GetAllTopicParentResponse response = new GetAllTopicParentResponse();
        response.setTopicParentDtos(topicParentDtos);
        return response;
    }

    private TopicParentDto toTopicParentDto(TopicParent topicParent) {
        TopicParentDto topicParentDto = new TopicParentDto();
        BeanUtils.copyProperties(topicParent, topicParentDto);
        return topicParentDto;
    }
}
