package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.response.topic.GetTopicByTopicParentResponse;
import edu.kpdteti.backend.model.response.topic.GetTopicResponse;

public interface TopicService {

    GetTopicByTopicParentResponse getTopicByTopicParent(Long topicParentId);

    GetTopicResponse getTopic(Long topicId);

}
