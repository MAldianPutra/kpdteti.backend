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

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public DeletePublicationResponse deletePublication(Long publicationId) {
        Publication publication = publicationRepository.findByPublicationId(publicationId);
        publicationRepository.delete(publication);
        DeletePublicationResponse response = new DeletePublicationResponse();
        // Delete PDF, later
        BeanUtils.copyProperties(publication, response);
        return response;
    }

    @Override
    public DownloadPublicationResponse downloadPublication(Long publicationId) {
        Publication publication = publicationRepository.findByPublicationId(publicationId);
        DownloadPublicationResponse response = new DownloadPublicationResponse();
        BeanUtils.copyProperties(publication, response);
        return response;
    }

    @Override
    public GetPublicationByAuthorResponse getPublicationByAuthor(Long authorId) {
        return null;
    }

    @Override
    public GetPublicationByTopicResponse getPublicationByTopic(Long topicId) {
        return null;
    }

    @Override
    public GetPublicationResponse getPublication(Long publicationId) {
        return null;
    }

    @Override
    public PostPublicationResponse postPublication(PostPublicationRequest request) {
        return null;
    }

    @Override
    public UpdatePublicationResponse updatePublication(UpdatePublicationRequest request) {
        return null;
    }
}
