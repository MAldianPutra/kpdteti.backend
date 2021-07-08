package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.response.topicParent.GetAllTopicParentsResponse;

import java.util.List;

public interface TopicParentService {

    List<GetAllTopicParentsResponse> getAllTopicParents();

}
