package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.request.publication.PostPublicationRequest;
import edu.kpdteti.backend.model.request.publication.UpdatePublicationRequest;
import edu.kpdteti.backend.model.response.publication.*;

import java.util.List;

public interface PublicationService {

    DeletePublicationResponse deletePublication(Long publicationId);

    DownloadPublicationResponse downloadPublication(Long publicationId);

    List<GetPublicationsByAuthorResponse> getPublicationsByAuthor(Long authorId);

    List<GetPublicationsByTopicResponse> getPublicationsByTopic(Long topicId);

    GetPublicationResponse getPublication(Long publicationId);

    PostPublicationResponse postPublication(PostPublicationRequest request);

    UpdatePublicationResponse updatePublication(UpdatePublicationRequest request);

}
