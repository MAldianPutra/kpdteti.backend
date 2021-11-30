package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.request.publication.PostPublicationRequest;
import edu.kpdteti.backend.model.request.publication.UpdatePublicationRequest;
import edu.kpdteti.backend.model.response.publication.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface PublicationService {

    DeletePublicationResponse deletePublication(String publicationId) throws ClassNotFoundException;

    DownloadPublicationResponse downloadPublication(String publicationId);

    List<GetPublicationsByAuthorResponse> getPublicationsByAuthor(String authorId);

    List<GetPublicationsByTopicResponse> getPublicationsByTopic(String topicOrParentId);

    List<GetAllPublicationResponse> getAllPublications();

    GetPublicationResponse getPublication(String publicationId);

    PostPublicationResponse postPublication(PostPublicationRequest request) throws URISyntaxException, SAXException, IOException, JAXBException;

    UpdatePublicationResponse updatePublication(UpdatePublicationRequest request);

}
