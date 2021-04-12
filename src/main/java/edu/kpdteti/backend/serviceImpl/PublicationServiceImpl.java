package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.Author;
import edu.kpdteti.backend.entity.Publication;
import edu.kpdteti.backend.entity.Topic;
import edu.kpdteti.backend.entity.User;
import edu.kpdteti.backend.model.request.publication.PostPublicationRequest;
import edu.kpdteti.backend.model.request.publication.UpdatePublicationRequest;
import edu.kpdteti.backend.model.response.publication.*;
import edu.kpdteti.backend.repository.AuthorRepository;
import edu.kpdteti.backend.repository.PublicationRepository;
import edu.kpdteti.backend.repository.TopicRepository;
import edu.kpdteti.backend.repository.UserRepository;
import edu.kpdteti.backend.service.PublicationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    private final AuthorRepository authorRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository, AuthorRepository authorRepository, TopicRepository topicRepository, UserRepository userRepository) {
        this.publicationRepository = publicationRepository;
        this.authorRepository = authorRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
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
    public List<GetPublicationsByAuthorResponse> getPublicationsByAuthor(Long authorId) {
        Author author = authorRepository.findByAuthorId(authorId);
        List<Publication> publications = publicationRepository.findAllByAuthors(author);
        List<GetPublicationsByAuthorResponse> responses = new ArrayList<>();
        publications.forEach(publication -> {
            GetPublicationsByAuthorResponse response = new GetPublicationsByAuthorResponse();
            BeanUtils.copyProperties(publication, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public List<GetPublicationsByTopicResponse> getPublicationsByTopic(Long topicId) {
        Topic topic = topicRepository.findByTopicId(topicId);
        List<Publication> publications = publicationRepository.findAllByTopics(topic);
        List<GetPublicationsByTopicResponse> responses = new ArrayList<>();
        publications.forEach(publication -> {
            GetPublicationsByTopicResponse response = new GetPublicationsByTopicResponse();
            BeanUtils.copyProperties(publication, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public GetPublicationResponse getPublication(Long publicationId) {
        Publication publication = publicationRepository.findByPublicationId(publicationId);
        GetPublicationResponse response = new GetPublicationResponse();
        BeanUtils.copyProperties(publication, response);
        return response;
    }

    @Override
    public PostPublicationResponse postPublication(PostPublicationRequest request) {
        // Set the AI later
        Set<Author> authors = new HashSet<>();
        request.getAuthorIds().forEach(id -> {
            Author author = authorRepository.findByAuthorId(id);
            authors.add(author);
        });
        Set<Topic> topics = new HashSet<>();
        request.getTopicIds().forEach(id -> {
            Topic topic = topicRepository.findByTopicId(id);
            topics.add(topic);
        });
        User user = userRepository.findByUserId(request.getUserId());
        Publication publication = Publication.builder()
                .user(user)
                .authors(authors)
                .topics(topics)
                .publicationCreatedAt(LocalDateTime.now())
                .publicationLastUpdated(LocalDateTime.now())
                .build();
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
        Publication updatedPublication = publicationRepository.save(publication);
        UpdatePublicationResponse response = new UpdatePublicationResponse();
        BeanUtils.copyProperties(updatedPublication, response);
        return response;
    }
}
