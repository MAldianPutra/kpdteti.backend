package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.request.admin.*;
import edu.kpdteti.backend.model.response.admin.*;

public interface AdminService {

    DeleteAuthorResponse deleteAuthor(String authorId);

    DeleteTopicParentResponse deleteTopicParent(String topicParentId);

    DeleteTopicResponse deleteTopic(String topicId);

    PostAuthorResponse postAuthor(PostAuthorRequest request);

    PostTopicParentResponse postTopicParent(PostTopicParentRequest request);

    PostTopicResponse postTopic(PostTopicRequest request);

}
