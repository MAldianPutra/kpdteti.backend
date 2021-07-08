package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.Author;
import edu.kpdteti.backend.entity.Publication;
import edu.kpdteti.backend.entity.Topic;
import edu.kpdteti.backend.entity.dto.AuthorDto;
import edu.kpdteti.backend.entity.dto.TopicDto;
import edu.kpdteti.backend.enums.IdGeneratorEnum;
import edu.kpdteti.backend.model.request.publication.PostPublicationRequest;
import edu.kpdteti.backend.model.request.publication.UpdatePublicationRequest;
import edu.kpdteti.backend.model.response.publication.*;
import edu.kpdteti.backend.repository.AuthorRepository;
import edu.kpdteti.backend.repository.PublicationRepository;
import edu.kpdteti.backend.repository.TopicRepository;
import edu.kpdteti.backend.service.PublicationService;
import edu.kpdteti.backend.util.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    private final AuthorRepository authorRepository;
    private final TopicRepository topicRepository;
    private final IdGenerator idGenerator;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository, AuthorRepository authorRepository, TopicRepository topicRepository, IdGenerator idGenerator) {
        this.publicationRepository = publicationRepository;
        this.authorRepository = authorRepository;
        this.topicRepository = topicRepository;
        this.idGenerator = idGenerator;
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
        List<AuthorDto> authorDtos = new ArrayList<>();
        request.getAuthorIds().forEach(id -> {
            Author author = authorRepository.findByAuthorId(id);
            AuthorDto authorDto = new AuthorDto();
            BeanUtils.copyProperties(author, authorDto);
            authorDtos.add(authorDto);
        });
        List<TopicDto> topicDtos = new ArrayList<>();
        request.getTopicIds().forEach(id -> {
            Topic topic = topicRepository.findByTopicId(id);
            TopicDto topicDto = new TopicDto();
            BeanUtils.copyProperties(topic, topicDto);
            topicDtos.add(topicDto);
        });

        Publication publication = Publication.builder()
                .publicationId(idGenerator.generateId(IdGeneratorEnum.PUBLICATION))
                .authorDto(authorDtos)
                .topicDto(topicDtos)
                .publicationCreatedAt(LocalDateTime.now())
                .publicationLastUpdated(LocalDateTime.now())
                .build();
        BeanUtils.copyProperties(request, publication);
        Publication savedPublication = publicationRepository.save(publication);
        PostPublicationResponse response = new PostPublicationResponse();
        BeanUtils.copyProperties(savedPublication, response);
        return response;
    }

    @Override
    public UpdatePublicationResponse updatePublication(UpdatePublicationRequest request) {
        Publication publication = publicationRepository.findByPublicationId(request.getPublicationId());
        BeanUtils.copyProperties(request, publication);
        publication.setPublicationLastUpdated(LocalDateTime.now());
        publicationRepository.save(publication);
        UpdatePublicationResponse response = new UpdatePublicationResponse();
        BeanUtils.copyProperties(publication, response);
        return response;
    }
}
