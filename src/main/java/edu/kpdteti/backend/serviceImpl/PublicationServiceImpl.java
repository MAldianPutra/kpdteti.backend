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
import edu.kpdteti.backend.util.IdGeneratorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    private final AuthorRepository authorRepository;
    private final TopicRepository topicRepository;
    private final IdGeneratorUtil idGeneratorUtil;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository, AuthorRepository authorRepository, TopicRepository topicRepository, IdGeneratorUtil idGeneratorUtil) {
        this.publicationRepository = publicationRepository;
        this.authorRepository = authorRepository;
        this.topicRepository = topicRepository;
        this.idGeneratorUtil = idGeneratorUtil;
    }

    @Override
    public DeletePublicationResponse deletePublication(String publicationId) {
        if(publicationRepository.existsById(publicationId)) {
            publicationRepository.deleteById(publicationId);
            return DeletePublicationResponse.builder()
                    .message("Success")
                    .build();
        } else {
            throw new EntityNotFoundException("Publication not found with id " + publicationId);
        }
    }

    @Override
    public DownloadPublicationResponse downloadPublication(String publicationId) {
        Publication publication = publicationRepository.findByPublicationId(publicationId);
        if(publication == null) {
            throw new EntityNotFoundException("Publication not found with id " + publicationId);
        }
        DownloadPublicationResponse response = new DownloadPublicationResponse();
        BeanUtils.copyProperties(publication, response);
        return response;
    }

    @Override
    public List<GetPublicationsByAuthorResponse> getPublicationsByAuthor(String authorId) {
        List<Publication> publications = publicationRepository.findAllByAuthorDto_AuthorId(authorId);
        if(publications.isEmpty()) {
            throw new EntityNotFoundException("Publication not found with authorId " + authorId);
        }
        List<GetPublicationsByAuthorResponse> responses = new ArrayList<>();
        publications.forEach(publication -> {
            GetPublicationsByAuthorResponse response = new GetPublicationsByAuthorResponse();
            BeanUtils.copyProperties(publication, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public List<GetPublicationsByTopicOrParentResponse> getPublicationsByTopicOrParent(String topicOrParentId) {
        if(topicOrParentId.startsWith("par-")) {
            List<Topic> topics = topicRepository.findAllByTopicParentDto_TopicParentId(topicOrParentId);
            if(topics.isEmpty()) {
                throw new EntityNotFoundException("Publication not found with topicParentId " + topicOrParentId);
            }
            List<Publication> publications = new ArrayList<>();
            topics.forEach(topic -> {
                List<Publication> publicationsByTopic = publicationRepository
                        .findAllByTopicDto_TopicId(topic.getTopicId());
                publications.addAll(publicationsByTopic);
            });
            List<GetPublicationsByTopicOrParentResponse> responses = new ArrayList<>();
            publications.forEach(publication -> {
                GetPublicationsByTopicOrParentResponse response = new GetPublicationsByTopicOrParentResponse();
                BeanUtils.copyProperties(publication, response);
                responses.add(response);
            });
            return responses;
        }
        if(topicOrParentId.startsWith("top-")) {
            List<Publication> publications = publicationRepository.findAllByTopicDto_TopicId(topicOrParentId);
            if(publications.isEmpty()) {
                throw new EntityNotFoundException("Publication not found with topicId " + topicOrParentId);
            }
            List<GetPublicationsByTopicOrParentResponse> responses = new ArrayList<>();
            publications.forEach(publication -> {
                GetPublicationsByTopicOrParentResponse response = new GetPublicationsByTopicOrParentResponse();
                BeanUtils.copyProperties(publication, response);
                responses.add(response);
            });
            return responses;
        }
        else {
            throw new EntityNotFoundException("The ID " + topicOrParentId + " is not allowed or not found.");
        }
    }

    @Override
    public GetPublicationResponse getPublication(String publicationId) {
        Publication publication = publicationRepository.findByPublicationId(publicationId);
        if(publication == null) {
            throw new EntityNotFoundException("Publication not found with id " + publicationId);
        }
        GetPublicationResponse response = new GetPublicationResponse();
        BeanUtils.copyProperties(publication, response);
        return response;
    }

    @Override
    public List<GetPublicationResponse> getAllPublications() {
        List<Publication> publications = publicationRepository.findAll();
        if(publications.isEmpty()) {
            throw new EntityNotFoundException("No Publication in database");
        }
        List<GetPublicationResponse> responses = new ArrayList<>();
        publications.forEach(publication -> {
            GetPublicationResponse response = new GetPublicationResponse();
            BeanUtils.copyProperties(publication, response);
            responses.add(response);
        });
        return responses;
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
        if(authorDtos.isEmpty()) {
            throw new EntityNotFoundException("Author not found with id " + request.getAuthorIds());
        }
        List<TopicDto> topicDtos = new ArrayList<>();
        if(!CollectionUtils.isEmpty(request.getTopicIds())) {
            request.getTopicIds().forEach(id -> {
                Topic topic = topicRepository.findByTopicId(id);
                TopicDto topicDto = new TopicDto();
                BeanUtils.copyProperties(topic, topicDto);
                topicDtos.add(topicDto);
            });
            if(topicDtos.isEmpty()) {
                throw new EntityNotFoundException("Topic not found with id " + request.getTopicIds());
            }
        }
        Publication publication = Publication.builder()
                .publicationId(idGeneratorUtil.generateId(IdGeneratorEnum.PUBLICATION))
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
        if(publication == null) {
            throw new EntityNotFoundException("Publication not found with id " + request.getPublicationId());
        }
        BeanUtils.copyProperties(request, publication);
        publication.setPublicationLastUpdated(LocalDateTime.now());
        publicationRepository.save(publication);
        UpdatePublicationResponse response = new UpdatePublicationResponse();
        BeanUtils.copyProperties(publication, response);
        return response;
    }
}
