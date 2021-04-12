package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.Publication;
import edu.kpdteti.backend.model.request.publication.PostPublicationRequest;
import edu.kpdteti.backend.model.request.publication.UpdatePublicationRequest;
import edu.kpdteti.backend.model.response.publication.*;
import edu.kpdteti.backend.repository.PublicationRepository;
import edu.kpdteti.backend.service.PublicationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public DeletePublicationResponse deletePublication(String publicationId) {
        try {
            Publication publication = publicationRepository.findByPublicationId(publicationId);
            publicationRepository.delete(publication);
            DeletePublicationResponse response = new DeletePublicationResponse();
            // Delete PDF, later
            BeanUtils.copyProperties(publication, response);
            return response;
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("No Publication with id " + publicationId);
        }

    }

    @Override
    public DownloadPublicationResponse downloadPublication(String publicationId) {
        Publication publication = publicationRepository.findByPublicationId(publicationId);
        DownloadPublicationResponse response = new DownloadPublicationResponse();
        BeanUtils.copyProperties(publication, response);
        return response;
    }

    @Override
    public List<GetPublicationsByAuthorResponse> getPublicationsByAuthor(String authorId) {
        List<Publication> publications = publicationRepository.findAllByAuthorDto_AuthorId(authorId);
        List<GetPublicationsByAuthorResponse> responses = new ArrayList<>();
        publications.forEach(publication -> {
            GetPublicationsByAuthorResponse response = new GetPublicationsByAuthorResponse();
            BeanUtils.copyProperties(publication, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public List<GetPublicationsByTopicResponse> getPublicationsByTopic(String topicId) {
        List<Publication> publications = publicationRepository.findAllByTopicDto_TopicId(topicId);
        List<GetPublicationsByTopicResponse> responses = new ArrayList<>();
        publications.forEach(publication -> {
            GetPublicationsByTopicResponse response = new GetPublicationsByTopicResponse();
            BeanUtils.copyProperties(publication, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public GetPublicationResponse getPublication(String publicationId) {
        Publication publication = publicationRepository.findByPublicationId(publicationId);
        GetPublicationResponse response = new GetPublicationResponse();
        BeanUtils.copyProperties(publication, response);
        return response;
    }

    @Override
    public PostPublicationResponse postPublication(PostPublicationRequest request) {
        return null;
    }

    @Override
    public UpdatePublicationResponse updatePublication(UpdatePublicationRequest request) {
        Publication publication = publicationRepository.findByPublicationId(request.getPublicationId());
        BeanUtils.copyProperties(request, publication);
        publication.setPublicationLastUpdated(LocalDateTime.now());
        Publication updatedPublication = publicationRepository.save(publication);
        UpdatePublicationResponse response = new UpdatePublicationResponse();
        BeanUtils.copyProperties(updatedPublication, response);
        return response;
    }
}
