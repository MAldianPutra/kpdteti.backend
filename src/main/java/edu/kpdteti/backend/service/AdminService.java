package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.request.admin.*;
import edu.kpdteti.backend.model.response.admin.*;

public interface AdminService {

    DeleteAuthorResponse deleteAuthor(DeleteAuthorRequest request);

    DeleteTopicParentResponse deleteTopicParent(DeleteTopicParentRequest request);

    DeleteTopicResponse deleteTopic(DeleteTopicRequest request);

    PostAuthorResponse postAuthor(PostAuthorRequest request);

    PostTopicParentResponse postTopicParent(PostTopicParentRequest request);

    PostTopicResponse postTopic(PostTopicRequest request);

}
