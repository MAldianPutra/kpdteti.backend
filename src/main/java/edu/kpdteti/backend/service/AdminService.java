package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.request.admin.*;
import edu.kpdteti.backend.model.response.admin.*;

import java.util.List;

public interface AdminService {

    DeleteAuthorResponse deleteAuthor(String authorId);

    DeleteTopicResponse deleteTopic(String topicId);

    PostAuthorResponse postAuthor(PostAuthorRequest request);

    PostTopicResponse postTopic(PostTopicRequest request);

    List<PopulateTopicResponse> populateTopic();

}
