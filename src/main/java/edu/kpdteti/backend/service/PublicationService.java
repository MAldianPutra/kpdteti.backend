package edu.kpdteti.backend.service;

import edu.kpdteti.backend.enums.SearchTypeEnum;
import edu.kpdteti.backend.model.request.publication.PostPublicationRequest;
import edu.kpdteti.backend.model.request.publication.SearchPublicationRequest;
import edu.kpdteti.backend.model.request.publication.UpdatePublicationRequest;
import edu.kpdteti.backend.model.response.publication.*;
import org.apache.commons.math3.util.Pair;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface PublicationService {

    DeletePublicationResponse deletePublication(String publicationId) throws ClassNotFoundException;

    DownloadPublicationResponse downloadPublication(String publicationId);

    List<GetPublicationsByAuthorResponse> getPublicationsByAuthor(String authorId, Integer page, Boolean usePage);

    List<GetPublicationsByTopicResponse> getPublicationsByTopic(String topicOrParentId, Integer page, Boolean usePage);

    List<GetAllPublicationResponse> getAllPublications(Integer page, Boolean usePage);

    GetPublicationResponse getPublication(String publicationId);

    List<SearchPublicationResponse> searchPublication(String searchKey, Integer page, Boolean usePage);

    PostPublicationResponse postPublication(PostPublicationRequest request) throws URISyntaxException, SAXException, IOException, JAXBException;

    UploadPublicationResponse uploadPublication(String publicationId, MultipartFile file) throws IOException, URISyntaxException;

    UpdatePublicationResponse updatePublication(UpdatePublicationRequest request);

}
