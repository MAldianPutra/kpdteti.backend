package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.request.admin.*;
import edu.kpdteti.backend.model.response.admin.*;

public interface AdminService {

    DeleteAuthorResponse deleteAuthor(Long authorId);

    DeleteTopicParentResponse deleteTopicParent(Long topicParentId);

    DeleteTopicResponse deleteTopic(Long topicId);

    PostAuthorResponse postAuthor(PostAuthorRequest request);

    PostTopicParentResponse postTopicParent(PostTopicParentRequest request);

    PostTopicResponse postTopic(PostTopicRequest request);

}
