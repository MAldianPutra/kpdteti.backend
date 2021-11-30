package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.response.topic.GetTopicsByParentResponse;
import edu.kpdteti.backend.model.response.topic.GetTopicResponse;

import java.util.List;

public interface TopicService {

    List<GetTopicsByParentResponse> getTopicsByParent(String topicParentId);

    GetTopicResponse getTopic(String topicId);

}
