package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.Publication;
import edu.kpdteti.backend.entity.PublicationAuthor;
import edu.kpdteti.backend.model.dto.PublicationDto;
import edu.kpdteti.backend.model.request.publication.PostPublicationRequest;
import edu.kpdteti.backend.model.request.publication.UpdatePublicationRequest;
import edu.kpdteti.backend.model.response.publication.*;
import edu.kpdteti.backend.repository.PublicationAuthorRepository;
import edu.kpdteti.backend.repository.PublicationRepository;
import edu.kpdteti.backend.repository.PublicationTopicRepository;
import edu.kpdteti.backend.service.PublicationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PublicationServiceImpl implements PublicationService {

    private PublicationRepository publicationRepository;
    private PublicationAuthorRepository publicationAuthorRepository;
    private PublicationTopicRepository publicationTopicRepository;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository, PublicationAuthorRepository publicationAuthorRepository, PublicationTopicRepository publicationTopicRepository) {
        this.publicationRepository = publicationRepository;
        this.publicationAuthorRepository = publicationAuthorRepository;
        this.publicationTopicRepository = publicationTopicRepository;
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
//        Set<PublicationAuthor> publicationAuthors = publicationAuthorRepository.findAllByAuthor_AuthorId(authorId);
//        Set<Publication> publications = publicationRepository.findAllByPublicationAuthors(publicationAuthors);
//        GetPublicationByAuthorResponse response = new GetPublicationByAuthorResponse();
//        List<PublicationDto> publicationDtos = new ArrayList<>();
//        publications.forEach(publication -> {
//            PublicationDto publicationDto = new PublicationDto();
//            BeanUtils.copyProperties(publication, publicationDto);
//            publicationDtos.add(publicationDto);
//        });
//        response.setPublicationDtos(publicationDtos);
//        return response;
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
