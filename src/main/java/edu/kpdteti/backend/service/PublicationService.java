package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.request.publication.PostPublicationRequest;
import edu.kpdteti.backend.model.request.publication.UpdatePublicationRequest;
import edu.kpdteti.backend.model.response.publication.*;

import java.util.List;

public interface PublicationService {

    DeletePublicationResponse deletePublication(String publicationId) throws ClassNotFoundException;

    DownloadPublicationResponse downloadPublication(String publicationId);

    List<GetPublicationsByAuthorResponse> getPublicationsByAuthor(String authorId);

    List<GetPublicationsByTopicResponse> getPublicationsByTopic(String topicId);

    GetPublicationResponse getPublication(String publicationId);

    List<GetPublicationResponse> getAllPublications();

    PostPublicationResponse postPublication(PostPublicationRequest request);

    UpdatePublicationResponse updatePublication(UpdatePublicationRequest request);

}
