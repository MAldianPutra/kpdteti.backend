package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.response.topic.GetTopicsByTopicParentResponse;
import edu.kpdteti.backend.model.response.topic.GetTopicResponse;

import java.util.List;

public interface TopicService {

    List<GetTopicsByTopicParentResponse> getTopicsByTopicParent(String topicParentId);

    GetTopicResponse getTopic(String topicId);

}
